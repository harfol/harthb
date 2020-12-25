import * as React from 'react';
import { createMuiTheme, ThemeProvider } from '@material-ui/core/styles';
import thingsboardTheme from './styles/thingsboardTheme';
import ThingsboardSchemaForm from './json-form-schema-form';
import { JsonFormProps } from './json-form.models';

const tbTheme = createMuiTheme(thingsboardTheme);

class ReactSchemaForm extends React.Component<JsonFormProps, {}> {

  static defaultProps: JsonFormProps;

  constructor(props) {
    super(props);
  }

  render() {
    if (this.props.form.length > 0) {
      return <ThemeProvider theme={tbTheme}><ThingsboardSchemaForm {...this.props} /></ThemeProvider>;
    } else {
      return <div></div>;
    }
  }
}

ReactSchemaForm.defaultProps = {
  isFullscreen: false,
  schema: {},
  form: ['*'],
  groupInfoes: [],
  option: {
    formDefaults: {
      startEmpty: true
    }
  }
};

export default ReactSchemaForm;
