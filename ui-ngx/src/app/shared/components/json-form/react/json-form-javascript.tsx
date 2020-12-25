import * as React from 'react';
import ThingsboardAceEditor from './json-form-ace-editor';
import { JsonFormFieldProps, JsonFormFieldState } from '@shared/components/json-form/react/json-form.models';

class ThingsboardJavaScript extends React.Component<JsonFormFieldProps, JsonFormFieldState> {

    constructor(props) {
        super(props);
        this.onTidyJavascript = this.onTidyJavascript.bind(this);
    }

    onTidyJavascript(javascript: string): string {
        return js_beautify(javascript, {indent_size: 4, wrap_line_length: 60});
    }

    render() {
        return (
           <ThingsboardAceEditor {...this.props} mode='javascript' onTidy={this.onTidyJavascript} {...this.state}></ThingsboardAceEditor>
        );
    }
}

export default ThingsboardJavaScript;
