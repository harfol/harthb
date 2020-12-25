export interface ILayoutController {
  reload();
  resetHighlight();
  highlightWidget(widgetId: string, delay?: number);
  selectWidget(widgetId: string, delay?: number);
  pasteWidget($event: MouseEvent);
  pasteWidgetReference($event: MouseEvent);
}
