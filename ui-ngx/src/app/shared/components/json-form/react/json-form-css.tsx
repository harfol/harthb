import * as React from 'react';
import ThingsboardAceEditor from './json-form-ace-editor';
import { JsonFormFieldProps, JsonFormFieldState } from '@shared/components/json-form/react/json-form.models';
import { css_beautify } from 'js-beautify';

class ThingsboardCss extends React.Component<JsonFormFieldProps, JsonFormFieldState> {

    constructor(props) {
        super(props);
        this.onTidyCss = this.onTidyCss.bind(this);
    }

    onTidyCss(css: string): string {
        return css_beautify(css, {indent_size: 4});
    }

    render() {
        return (
            <ThingsboardAceEditor {...this.props} mode='css' onTidy={this.onTidyCss} {...this.state}></ThingsboardAceEditor>
        );
    }
}

export default ThingsboardCss;
