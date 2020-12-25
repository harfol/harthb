import {
  Compiler,
  Component,
  ComponentFactory,
  Injectable,
  Injector,
  NgModule,
  NgModuleRef,
  OnDestroy,
  Type
} from '@angular/core';
import { Observable, ReplaySubject } from 'rxjs';
import { CommonModule } from '@angular/common';

@NgModule()
export abstract class DynamicComponentModule implements OnDestroy {

  ngOnDestroy(): void {
  }

}

interface DynamicComponentModuleData {
  moduleRef: NgModuleRef<DynamicComponentModule>;
  moduleType: Type<DynamicComponentModule>;
}

@Injectable(
  {
    providedIn: 'root'
  }
)
export class DynamicComponentFactoryService {

  private dynamicComponentModulesMap = new Map<ComponentFactory<any>, DynamicComponentModuleData>();

  constructor(private compiler: Compiler,
              private injector: Injector) {
  }

  public createDynamicComponentFactory<T>(
                     componentType: Type<T>,
                     template: string,
                     modules?: Type<any>[]): Observable<ComponentFactory<T>> {
    const dymamicComponentFactorySubject = new ReplaySubject<ComponentFactory<T>>();
    const comp = this.createDynamicComponent(componentType, template);
    let moduleImports: Type<any>[] = [CommonModule];
    if (modules) {
      moduleImports = [...moduleImports, ...modules];
    }
    // noinspection AngularInvalidImportedOrDeclaredSymbol
    @NgModule({
      declarations: [comp],
      imports: moduleImports
    })
    class DynamicComponentInstanceModule extends DynamicComponentModule {}
    try {
      this.compiler.compileModuleAsync(DynamicComponentInstanceModule).then(
        (module) => {
          const moduleRef = module.create(this.injector);
          const factory = moduleRef.componentFactoryResolver.resolveComponentFactory(comp);
          this.dynamicComponentModulesMap.set(factory, {
            moduleRef,
            moduleType: module.moduleType
          });
          dymamicComponentFactorySubject.next(factory);
          dymamicComponentFactorySubject.complete();
        }
      ).catch(
        (e) => {
          dymamicComponentFactorySubject.error(e);
        }
      );
    } catch (e) {
      dymamicComponentFactorySubject.error(e);
    }
    return dymamicComponentFactorySubject.asObservable();
  }

  public destroyDynamicComponentFactory<T>(factory: ComponentFactory<T>) {
    const moduleData = this.dynamicComponentModulesMap.get(factory);
    if (moduleData) {
      moduleData.moduleRef.destroy();
      this.compiler.clearCacheFor(moduleData.moduleType);
      this.dynamicComponentModulesMap.delete(factory);
    }
  }

  private createDynamicComponent<T>(componentType: Type<T>, template: string): Type<T> {
    // noinspection AngularMissingOrInvalidDeclarationInModule
    return Component({
      template
    })(componentType);
  }

}
