import { BaseData } from '@shared/models/base-data';
import { EntityId } from '@shared/models/id/entity-id';
import { NavTreeNode, NodesCallback } from '@shared/components/nav-tree.component';
import { Datasource } from '@shared/models/widget.models';
import { isDefined, isUndefined } from '@core/utils';
import { EntityRelationsQuery, EntitySearchDirection, RelationTypeGroup } from '@shared/models/relation.models';
import { EntityType } from '@shared/models/entity-type.models';

export interface EntitiesHierarchyWidgetSettings {
  nodeRelationQueryFunction: string;
  nodeHasChildrenFunction: string;
  nodeOpenedFunction: string;
  nodeDisabledFunction: string;
  nodeIconFunction: string;
  nodeTextFunction: string;
  nodesSortFunction: string;
}

export interface HierarchyNodeContext {
  parentNodeCtx?: HierarchyNodeContext;
  entity: BaseData<EntityId>;
  childrenNodesLoaded?: boolean;
  level?: number;
  data: {[key: string]: any};
}

export interface HierarchyNavTreeNode extends NavTreeNode {
  data?: {
    datasource: HierarchyNodeDatasource;
    nodeCtx: HierarchyNodeContext;
    searchText?: string;
  };
}

export interface HierarchyNodeDatasource extends Datasource {
  nodeId: string;
}

export interface HierarchyNodeIconInfo {
  iconUrl?: string;
  materialIcon?: string;
}

export type NodeRelationQueryFunction = (nodeCtx: HierarchyNodeContext) => EntityRelationsQuery | 'default';
export type NodeTextFunction = (nodeCtx: HierarchyNodeContext) => string;
export type NodeDisabledFunction = (nodeCtx: HierarchyNodeContext) => boolean;
export type NodeIconFunction = (nodeCtx: HierarchyNodeContext) => HierarchyNodeIconInfo | 'default';
export type NodeOpenedFunction = (nodeCtx: HierarchyNodeContext) => boolean;
export type NodeHasChildrenFunction = (nodeCtx: HierarchyNodeContext) => boolean;
export type NodesSortFunction = (nodeCtx1: HierarchyNodeContext, nodeCtx2: HierarchyNodeContext) => number;

export function loadNodeCtxFunction<F extends (...args: any[]) => any>(functionBody: string, argNames: string, ...args: any[]): F {
  let nodeCtxFunction: F = null;
  if (isDefined(functionBody) && functionBody.length) {
    try {
      nodeCtxFunction = new Function(argNames, functionBody) as F;
      const res = nodeCtxFunction.apply(null, args);
      if (isUndefined(res)) {
        nodeCtxFunction = null;
      }
    } catch (e) {
      nodeCtxFunction = null;
    }
  }
  return nodeCtxFunction;
}

export function materialIconHtml(materialIcon: string): string {
  return '<mat-icon class="node-icon material-icons" role="img" aria-hidden="false">' + materialIcon + '</mat-icon>';
}

export function iconUrlHtml(iconUrl: string): string {
  return '<div class="node-icon" style="background-image: url(' + iconUrl + ');">&nbsp;</div>';
}

export const defaultNodeRelationQueryFunction: NodeRelationQueryFunction = nodeCtx => {
  const entity = nodeCtx.entity;
  const query: EntityRelationsQuery = {
    parameters: {
      rootId: entity.id.id,
      rootType: entity.id.entityType as EntityType,
      direction: EntitySearchDirection.FROM,
      relationTypeGroup: RelationTypeGroup.COMMON,
      maxLevel: 1
    },
    filters: [
      {
        relationType: 'Contains',
        entityTypes: []
      }
    ]
  };
  return query;
};

export const defaultNodeIconFunction: NodeIconFunction = nodeCtx => {
  let materialIcon = 'insert_drive_file';
  const entity = nodeCtx.entity;
  if (entity && entity.id && entity.id.entityType) {
    switch (entity.id.entityType as EntityType | string) {
      case 'function':
        materialIcon = 'functions';
        break;
      case EntityType.DEVICE:
        materialIcon = 'devices_other';
        break;
      case EntityType.ASSET:
        materialIcon = 'domain';
        break;
      case EntityType.TENANT:
        materialIcon = 'supervisor_account';
        break;
      case EntityType.CUSTOMER:
        materialIcon = 'supervisor_account';
        break;
      case EntityType.USER:
        materialIcon = 'account_circle';
        break;
      case EntityType.DASHBOARD:
        materialIcon = 'dashboards';
        break;
      case EntityType.ALARM:
        materialIcon = 'notifications_active';
        break;
      case EntityType.ENTITY_VIEW:
        materialIcon = 'view_quilt';
        break;
    }
  }
  return {
    materialIcon
  };
};

export const defaultNodeOpenedFunction: NodeOpenedFunction = nodeCtx => {
  return nodeCtx.level <= 4;
};

export const defaultNodesSortFunction: NodesSortFunction = (nodeCtx1, nodeCtx2) => {
  let result = nodeCtx1.entity.id.entityType.localeCompare(nodeCtx2.entity.id.entityType);
  if (result === 0) {
    result = nodeCtx1.entity.name.localeCompare(nodeCtx2.entity.name);
  }
  return result;
};
