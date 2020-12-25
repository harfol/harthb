import * as React from 'react';
import ThingsboardAceEditor from './json-form-ace-editor';
import { JsonFormFieldProps, JsonFormFieldState } from '@shared/components/json-form/react/json-form.models';

class ThingsboardJson extends React.Component<JsonFormFieldProps, JsonFormFieldState> {

    constructor(props) {
        super(props);
        this.onTidyJson = this.onTidyJson.bind(this);
    }

    onTidyJson(json: string): string {
        return js_beautify(json, {indent_size: 4});
    }

    render() {
        return (
            <ThingsboardAceEditor {...this.props} mode='json' onTidy={this.onTidyJson} {...this.state}></ThingsboardAceEditor>
        );
    }
}

export default ThingsboardJson;
