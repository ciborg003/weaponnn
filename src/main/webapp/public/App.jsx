import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.css';
import SkyLight from 'react-skylight';
import SkyLightStateless from 'react-skylight';
import Alert from 'react-s-alert';
import 'react-s-alert/dist/s-alert-default.css';
import 'react-s-alert/dist/s-alert-css-effects/slide.css';
import { Router, Route, Switch } from 'react-router';


class App extends React.Component {
    constructor(props){
        super(props);
        this.state = {user: null};
    }

    render() {
        return (
            <div>
                <Header user={this.state.user}/>
                <Content user={this.state.user}/>
            </div>
        );
    }
}

class Content extends Component {

    constructor(props){
        super(props);
        this.state = {user: this.props.user};
        this.loadCurrentUser();
    }

    loadCurrentUser() {
        fetch("http://localhost:8080/api/user",{
            credentials: 'same-origin'
        }).then(response=>{
            return response.json()
        }).then(data => {
            this.setState({
                user: data
            })
        })
    }

    isLoggedIn(){
        return this.state.user && this.state.user.id;
    }

    render() {
        if (!this.isLoggedIn()){
            return (
                <div>
                    <Login user={this.state.user}/>
                </div>
            )
        }

        return (
            <Projects/>
        )
    }

}
class Header extends Component {
    render() {
        return (
            <header className="center"><h1>HEader</h1></header>
        )
    }

}

class Login extends Component {

    render() {
        return (
            <div>
                <a href="http://localhost:8080/login">Login</a>
                <Register/>
            </div>
        )
    }

}

class Projects extends Component {
    render() {
        return (
            <ProjectList/>
        )
    }
}

class ProjectList extends Component {

    constructor(props){
        super(props);
        this.state = {
            projects: []
        };

        this.loadProjects();
    }

    loadProjects(){
        fetch("http://localhost:8080/api/projects",
            {
                credentials: "same-origin"
            }).then(response => {
            return response.json();
        }).then(body => {
            this.setState({
                projects: body
            });
        })
    }

    componentDidMount(){
        $(".button-collapse").sideNav();
    }

    render() {
        var projList = this.state.projects.map(project => <ProjectItem key={project.id} project={project} />);

        return (
            <div>
                <ul id="slide-out" className="side-nav fixed">
                    <h2>Project List</h2>
                    <li><div className="center">
                        <button className="btn waves-effect waves-light" onClick={() => this.refs.simpleDialog.show()}>New project</button>
                    </div></li>
                    {projList}
                </ul>
                <a href="#" data-activates="slide-out" className="button-collapse">
                    <i className="material-icons">menu</i>
                </a>
                <main style={{paddingLeft:300}}>
                    <CreateProject loadProjects={this.loadProjects()}/>
                </main>
                {/*<Router>*/}
                    {/*<Switch>*/}
                        {/*<Route path="/:id" component={TaskList}/>*/}
                    {/*</Switch>*/}
                {/*</Router>*/}
            </div>
        )
    }
}

class ProjectItem extends Component {

    constructor(props) {
        super(props);
    }

    render()
    {
        return (
            <li><a href="#!">{this.props.project.name}</a></li>
        )
    }
}

class CreateProject extends Component {
    constructor(props) {
        super(props);
        this.state = {name: ''};
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);

    }

    handleChange(event) {
        this.setState(
            {name: event.target.value}
        );
    }

    handleSubmit(event) {
        var newStudent = {firstname: this.state.firstname, lastname: this.state.lastname, email: this.state.email};
        var newProject = {
            id: null,
            name: this.state.name,
            manager: null
        }
        this.refs.simpleDialog.hide();

        fetch("http://localhost:8080/api/projects/save",{
            method: "post",
            'credentials': 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newProject)
        }).then(response => {
            if (response.status == 200){
                this.props.loadProjects();
            }
        });
    }

    render() {
        return (
            <div>
                <SkyLight hideOnOverlayClicked ref="simpleDialog">
                    <div className="panel panel-default">
                        <div className="panel-heading">Create project</div>
                        <div className="panel-body">
                                <div className="col m4">
                                    <input type="text" placeholder="Name" className="form-control"  name="name" onChange={this.handleChange}/>
                                </div>
                                <div className="col m2">
                                    <button type="button" className="btn waves-effect waves-light" onClick={this.handleSubmit}>Save</button>
                                </div>
                        </div>
                    </div>
                </SkyLight>
            </div>
        );
    }
}

class Register extends Component {

    constructor(props){
        super(props)
        this.state = {
            login: "",
            password: "",
            fn: "",
            ln: "",
            role: "1",
            error: false,
            successfulRegistration: false
        }

        this.handleLogin = this.handleLogin.bind(this);
        this.handlePassword = this.handlePassword.bind(this);
        this.handleFN = this.handleFN.bind(this);
        this.handleLN = this.handleLN.bind(this);
        this.register = this.register.bind(this);
        this.handleRole = this.handleRole.bind(this)
    }

    handleLogin(e){
        this.setState({login: e.target.value});
    }

    handlePassword(e){
        this.setState({password: e.target.value});
    }

    handleFN(e){
        this.setState({fn: e.target.value});
    }

    handleLN(e){
        this.setState({ln: e.target.value});
    }

    handleRole(e){
        this.setState({role: e.target.value});
        console.log(e.target.value);
    }

    register(){
        var user = {
            id: null,
            firstName: this.state.fn,
            lastName: this.state.fn,
            email: this.state.login,
            password: this.state.password,
            role: {
                id: +this.state.role,
                role:""
            }
        }

        console.log(JSON.stringify(user));

        fetch("http://localhost:8080/api/user/register",{
            method: "post",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        }).then(response => {
            if (response.status != 200){

            } else {
                this.setState({
                    successfulRegistration: true
                })
            }
        }).catch(exc => {
            console.log("Exception:\n"+exc);
        })
    }

    render() {
        var error, successfulRegistration;
        if (this.state.error){
            error = <div>Login already exists</div>;
        }
        if (this.state.successfulRegistration){
            successfulRegistration = <h1>You've been registred successfully</h1>;
        }
        return (
            <div>
                {error}
                <input value={this.state.login} onChange={this.handleLogin} name="username" type="text" placeholder="login"/>
                <br/>
                <input value={this.state.password} onChange={this.handlePassword} name="password" type="password" placeholder="password"/>
                <br/>
                <input value={this.state.fn} onChange={this.handleFN} name="FN" type="text" placeholder="First Name"/>
                <br/>
                <input value={this.state.ln} onChange={this.handleLN} name="LN" type="text" placeholder="Last Name"/>
                <br/>
                <select value={this.state.role} onChange={this.handleRole}>
                    <option value="1">Manager</option>
                    <option value="2">Developer</option>
                </select>
                <br/>
                <button type="button" onClick={this.register}>Register</button>
                {successfulRegistration}
            </div>
        )
    }

}

class TaskList extends Component {

    constructor(props){
        super(props);
        this.state = {
            tasks: []
        }
    }

    loadTasks(){

    }

    render(){
        <h2>TaskList</h2>
    }
}

class TaskItem extends Component {

    constructor(props){
        super(props);
    }

    render(){
        return (<h3>TaskItem</h3>);
    }
}

ReactDOM.render(<App />, document.getElementById('root') );