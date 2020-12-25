import * as React from 'react';
import ThingsboardAceEditor from './json-form-ace-editor';
import { html_beautify } from 'js-beautify';
import { JsonFormFieldProps, JsonFormFieldState } from '@shared/components/json-form/react/json-form.models';

class ThingsboardHtml extends React.Component<JsonFormFieldProps, JsonFormFieldState> {

    constructor(props) {
        super(props);
        this.onTidyHtml = this.onTidyHtml.bind(this);
    }

    onTidyHtml(html: string): string {
        return html_beautify(html, {indent_size: 4});
    }

    render() {
        return (
            <ThingsboardAceEditor {...this.props} mode='html' onTidy={this.onTidyHtml} {...this.state}></ThingsboardAceEditor>
        );
    }
}

export default ThingsboardHtml;
