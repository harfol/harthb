import { Component, OnInit } from '@angular/core';
import { MenuService } from '@core/services/menu.service';
import { BreakpointObserver, BreakpointState } from '@angular/cdk/layout';
import { MediaBreakpoints } from '@shared/models/constants';
import { HomeSection } from '@core/services/menu.models';

@Component({
  selector: 'tb-home-links',
  templateUrl: './home-links.component.html',
  styleUrls: ['./home-links.component.scss']
})
export class HomeLinksComponent implements OnInit {

  homeSections$ = this.menuService.homeSections();

  cols = 2;

  constructor(private menuService: MenuService,
              public breakpointObserver: BreakpointObserver) {
  }

  ngOnInit() {
    this.updateColumnCount();
    this.breakpointObserver
      .observe([MediaBreakpoints.lg, MediaBreakpoints['gt-lg']])
      .subscribe((state: BreakpointState) => this.updateColumnCount());
  }

  private updateColumnCount() {
    this.cols = 2;
    if (this.breakpointObserver.isMatched(MediaBreakpoints.lg)) {
      this.cols = 3;
    }
    if (this.breakpointObserver.isMatched(MediaBreakpoints['gt-lg'])) {
      this.cols = 4;
    }
  }

  sectionColspan(section: HomeSection): number {
    if (this.breakpointObserver.isMatched(MediaBreakpoints['gt-sm'])) {
      let colspan = this.cols;
      if (section && section.places && section.places.length <= colspan) {
        colspan = section.places.length;
      }
      return colspan;
    } else {
      return 2;
    }
  }
}
