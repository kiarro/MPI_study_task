const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

const Item = styled(Button)(({ theme }) => ({
    padding: theme.spacing(2),
    textAlign: "left"
  }));

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {employees: []};
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/test'}).done(response => {
			this.setState({test: response.entity.text});
		});
	}

	render() {
		return (
			<Item>{this.state.test}</Item>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)