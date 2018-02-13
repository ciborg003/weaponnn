import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.css';
import SkyLight from 'react-skylight';
import SkyLightStateless from 'react-skylight';
import Alert from 'react-s-alert';
import 'react-s-alert/dist/s-alert-default.css';
import 'react-s-alert/dist/s-alert-css-effects/slide.css';
import { Router, Route, Switch, BrowserRouter, Link} from 'react-router-dom';


class App extends React.Component {
    constructor(props){
        super(props);
        this.state = {user: null};
    }

    render() {
        return (
            <div>
                <Header user={this.state.user}/>
                <Content/>
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
            <ProjectList/>
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

class ProjectList extends Component {

    constructor(props){
        super(props);
        this.state = {
            projects: [],
            selectedProject: null
        };

        this.loadProjects = this.loadProjects.bind(this);

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
        var taskList;
        if (this.state.selectedProject){
            taskList = <TaskList project={this.state.selectedProject} />
        }
        return (
            <div>
                <ul id="slide-out" className="side-nav fixed">
                    <h2>Project List</h2>
                    {projList}
                </ul>
                <a href="#" data-activates="slide-out" className="button-collapse">
                    <i className="material-icons">menu</i>
                </a>
                <main style={{paddingLeft:350}}>
                    {/*<CreateProject loadProjects={this.loadProjects()}/>*/}
                    <Switch>
                        <Route exact path="/" component={CreateProject} loadProjects={this.loadProjects}/>
                        <Route path="/project/:id" component={TaskList}/>
                        <Route path="/task/:id" component={Task}/>
                    </Switch>
                </main>
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
            <li><Link to={"/project/"+this.props.project.id}>{this.props.project.name}</Link></li>
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
                <button className="btn waves-effect waves-light" onClick={() => this.refs.simpleDialog.show()}>New project</button>
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

    componentDidMount() {
        var element = ReactDOM.findDOMNode(this.refs.dropdown)

        $(element).ready(function() {
            $('select').material_select();
        });
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
                <div className="input-field col s12">
                    <select ref="dropdown" value={this.state.role} onChange={this.handleRole}>
                        <option value="1">Manager</option>
                        <option value="2">Developer</option>
                    </select>
                    <label>Choose Role</label>
                </div>
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
        this.loadTasks();
    }

    loadTasks(id){
        fetch("http://localhost:8080/api/tasks/"+id,{
            method: "get",
            credentials: "same-origin"
        })
            .then(response => {
                if (response.status == 200){
                    return response.json();
                }
            }).then(body => {
                this.setState({
                    tasks: body
                })
        })
    }

    render(){
        var taskItems = this.state.tasks.map(task =><div> <TaskItem key={task.id} task={task}/><br/></div>);

        return(
            <div>
                <h2>{this.props.match.params.id} TaskList</h2>
                <CreateTask loadTasks={this.loadTasks} projId={this.props.match.params.id}/>
                {taskItems}
            </div>
        )
    }
}

class TaskItem extends Component {

    constructor(props){
        super(props);
    }

    render(){
        return (<Link  to={"/task/"+this.props.task.id}>{this.props.task.name}</Link>);
    }
}

class Task extends Component {
    constructor(props){
        super(props);
        this.state = {
            task: {
                name: "Title",
                status: "W",
                description: "Description"
            }
        }

        this.changeStatus = this.changeStatus.bind(this);
        this.loadTask(this.props.match.params.id);
    }

    changeStatus(e){
        var task = this.state.task;
        task.status = e.target.value;
        this.setState({
            task: task
        });
        fetch("http://localhost:8080/api/task/status",{
            method: "put",
            credentials: "same-origin",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.state.task)
        }).then(response => {
            if (response.status == 200){
            }
        })
    }

    loadTask(id){
        fetch("http://localhost:8080/api/task/"+id,{
            credentials: "same-origin"
        })
            .then(response => {
                if (response.status == 200){
                    return response.json();
                }
            }).then(body => {
                this.setState({
                    task: body
                });
        });
    }

    componentDidMount() {
        var elementTaskStatus = ReactDOM.findDOMNode(this.refs.dropdownTask)

        $(elementTaskStatus).ready(function() {
            $('select').material_select();
        });
        $(elementTaskStatus).on('change',this.changeStatus);
        $(elementTaskStatus).init({default: this.state.task.status});
    }

    render(){
        if (this.state.task) {
            return (<div>
                <h2>{this.state.task.name}</h2>
                Status:
                <div className="input-field col s12">
                    <select ref="dropdownTask" value={this.state.task.status} onChange={this.componentDidMount()}>
                        <option value="W">Waiting</option>
                        <option value="R">Realising</option>
                        <option value="I">Implementing</option>
                        <option value="V">Verifying</option>
                    </select>
                </div>
                <p>{this.state.task.description}</p>
            </div>)
        }

        return <h2>Unknown Task</h2>
    }
}

class CreateTask extends Component {
    constructor(props) {
        super(props);
        this.state = {name: '',description: ''};
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleChangeName = this.handleChangeName.bind(this);
        this.handleChangeDescription = this.handleChangeDescription.bind(this);

    }

    handleChangeName(event) {
        this.setState(
            {name: event.target.value}
        );
    }

    handleChangeDescription(event) {
        this.setState(
            {description: event.target.value}
        );
    }

    handleSubmit(event) {
        var newTask = {
            id: null,
            name: this.state.name,
            description: this.state.description,
            status: "W"
        }
        this.refs.taskCreator.hide();

        fetch("http://localhost:8080/api/task/save",{
            method: "post",
            'credentials': 'same-origin',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({task:newTask,projId:this.props.projId})
        }).then(response => {
            if (response.status == 200){
                windows
            }
        });
    }

    render() {
        return (
            <div>
                <button className="btn waves-effect waves-light" onClick={() => this.refs.taskCreator.show()}>New Task</button>
                <SkyLight hideOnOverlayClicked ref="taskCreator">
                    <div className="panel panel-default">
                        <div className="panel-heading">Create Task</div>
                        <div className="panel-body">
                            <div className="col m4">
                                <input type="text" placeholder="Name" className="form-control" onChange={this.handleChangeName}/>
                            </div>
                            <div className="col m4">
                                <input type="text" placeholder="Description" className="form-control" onChange={this.handleChangeDescription}/>
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

ReactDOM.render(<BrowserRouter><App /></BrowserRouter>, document.getElementById('root') );
