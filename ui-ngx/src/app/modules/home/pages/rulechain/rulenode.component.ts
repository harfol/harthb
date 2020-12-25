import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';
import { FcNodeComponent } from 'ngx-flowchart/dist/ngx-flowchart';
import { FcRuleNode, RuleNodeType } from '@shared/models/rule-node.models';
import { Router } from '@angular/router';

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'rule-node',
  templateUrl: './rulenode.component.html',
  styleUrls: ['./rulenode.component.scss']
})
export class RuleNodeComponent extends FcNodeComponent implements OnInit {

  iconUrl: SafeResourceUrl;
  RuleNodeType = RuleNodeType;

  constructor(private sanitizer: DomSanitizer,
              private router: Router) {
    super();
  }

  ngOnInit(): void {
    super.ngOnInit();
    if (this.node.iconUrl) {
      this.iconUrl = this.sanitizer.bypassSecurityTrustResourceUrl(this.node.iconUrl);
    }
  }

  openRuleChain($event: Event, node: FcRuleNode) {
    if ($event) {
      $event.stopPropagation();
    }
    if (node.targetRuleChainId) {
      this.router.navigateByUrl(`/ruleChains/${node.targetRuleChainId}`);
    }
  }
}
