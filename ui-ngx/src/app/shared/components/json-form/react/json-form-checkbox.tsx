import * as React from 'react';
import ThingsboardBaseComponent from './json-form-base-component';
import Checkbox from '@material-ui/core/Checkbox';
import { JsonFormFieldProps, JsonFormFieldState } from './json-form.models.js';
import FormControlLabel from '@material-ui/core/FormControlLabel';

class ThingsboardCheckbox extends React.Component<JsonFormFieldProps, JsonFormFieldState> {
    render() {
        return (
          <div>
          <FormControlLabel
            control={
              <Checkbox
                name={this.props.form.key.slice(-1)[0] + ''}
                value={this.props.form.key.slice(-1)[0]}
                checked={this.props.value || false}
                disabled={this.props.form.readonly}
                onChange={(e, checked) => {
                  this.props.onChangeValidate(e);
                }}
              />
            }
            label={this.props.form.title}
            />
          </div>
        );
    }
}

export default ThingsboardBaseComponent(ThingsboardCheckbox);
