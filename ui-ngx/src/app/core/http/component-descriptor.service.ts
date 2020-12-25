import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ComponentDescriptor, ComponentType } from '@shared/models/component-descriptor.models';
import { defaultHttpOptionsFromConfig, RequestConfig } from '@core/http/http-utils';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { RuleNodeType } from '@shared/models/rule-node.models';

@Injectable({
  providedIn: 'root'
})
export class ComponentDescriptorService {

  private componentsByType: Map<ComponentType | RuleNodeType, Array<ComponentDescriptor>> =
    new Map<ComponentType, Array<ComponentDescriptor>>();
  private componentsByClazz: Map<string, ComponentDescriptor> = new Map<string, ComponentDescriptor>();

  constructor(
    private http: HttpClient
  ) {
  }

  public getComponentDescriptorsByType(componentType: ComponentType, config?: RequestConfig): Observable<Array<ComponentDescriptor>> {
    const existing = this.componentsByType.get(componentType);
    if (existing) {
      return of(existing);
    } else {
      return this.http.get<Array<ComponentDescriptor>>(`/api/components/${componentType}`, defaultHttpOptionsFromConfig(config)).pipe(
        map((componentDescriptors) => {
          this.componentsByType.set(componentType, componentDescriptors);
          componentDescriptors.forEach((componentDescriptor) => {
            this.componentsByClazz.set(componentDescriptor.clazz, componentDescriptor);
          });
          return componentDescriptors;
        })
      );
    }
  }

  public getComponentDescriptorsByTypes(componentTypes: Array<ComponentType>,
                                        config?: RequestConfig): Observable<Array<ComponentDescriptor>> {
    let result: ComponentDescriptor[] = [];
    for (let i = componentTypes.length - 1; i >= 0; i--) {
      const componentType = componentTypes[i];
      const componentDescriptors = this.componentsByType.get(componentType);
      if (componentDescriptors) {
        result = result.concat(componentDescriptors);
        componentTypes.splice(i, 1);
      }
    }
    if (!componentTypes.length) {
      return of(result);
    } else {
      return this.http.get<Array<ComponentDescriptor>>(`/api/components?componentTypes=${componentTypes.join(',')}`,
        defaultHttpOptionsFromConfig(config)).pipe(
        map((componentDescriptors) => {
          componentDescriptors.forEach((componentDescriptor) => {
            let componentsList = this.componentsByType.get(componentDescriptor.type);
            if (!componentsList) {
              componentsList = new Array<ComponentDescriptor>();
              this.componentsByType.set(componentDescriptor.type, componentsList);
            }
            componentsList.push(componentDescriptor);
            this.componentsByClazz.set(componentDescriptor.clazz, componentDescriptor);
          });
          result = result.concat(componentDescriptors);
          return result;
        })
      );
    }
  }

  public getComponentDescriptorByClazz(componentDescriptorClazz: string, config?: RequestConfig): Observable<ComponentDescriptor> {
    const existing = this.componentsByClazz.get(componentDescriptorClazz);
    if (existing) {
      return of(existing);
    } else {
      return this.http.get<ComponentDescriptor>(`/api/component/${componentDescriptorClazz}`, defaultHttpOptionsFromConfig(config)).pipe(
        map((componentDescriptor) => {
          this.componentsByClazz.set(componentDescriptorClazz, componentDescriptor);
          return componentDescriptor;
        })
      );
    }
  }
}
