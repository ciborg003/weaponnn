import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.css';
import SkyLight from 'react-skylight';
import SkyLightStateless from 'react-skylight';
import Alert from 'react-s-alert';
import 'react-s-alert/dist/s-alert-default.css';
import 'react-s-alert/dist/s-alert-css-effects/slide.css';


class App extends React.Component {
    constructor(props){
        super(props);
        this.state = {user: null};
    }

    render() {
        return (
            <div>
                <Header user={this.state.user}/>
                <p className="App-intro">
                    To get started, edit <code>src/App.js</code> and save to reload.
                </p>
                <Content user={this.state.user}/>
            </div>
        );
    }
}
    	
class StudentTable extends React.Component {
    constructor(props) {
        super(props);
    }
    
    render() {
    var students = this.props.students.map(student =>
        <Student key={student._links.self.href} student={student} updateStudent={this.props.updateStudent} deleteStudent={this.props.deleteStudent}/>
    );

    return (
      <div>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Firstname</th><th>Lastname</th><th>Email</th><th> </th>
          </tr>
        </thead>
        <tbody>{students}</tbody>
      </table>
      </div>);
    }
}

class Student extends React.Component {
    constructor(props) {
        super(props);
        this.state = {editShow: false};
        this.deleteStudent = this.deleteStudent.bind(this);      
    }

    deleteStudent() {
        this.props.deleteStudent(this.props.student);
    } 

    render() {
        return (
          <tr>
            <td>{this.props.student.firstname}</td>
            <td>{this.props.student.lastname}</td>
            <td>{this.props.student.email}</td>
            <td>               
                <StudentUpdateForm updateStudent={this.props.updateStudent} student={this.props.student}/>          
            </td>
            <td>               
                <button className="btn btn-danger btn-xs" onClick={this.deleteStudent}>Delete</button>
            </td>
          </tr>
        );
    } 
}

class StudentForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {firstname: '', lastname: '', email: ''};
        this.handleSubmit = this.handleSubmit.bind(this);   
        this.handleChange = this.handleChange.bind(this);     
    }

    handleChange(event) {
        this.setState(
            {[event.target.name]: event.target.value}
        );
    }    
    
    handleSubmit(event) {
        event.preventDefault();
        console.log("Firstname: " + this.state.firstname);
        var newStudent = {firstname: this.state.firstname, lastname: this.state.lastname, email: this.state.email};
        this.props.createStudent(newStudent);    
        this.refs.simpleDialog.hide();    
    }
    
    render() {
        return (
          <div>
            <SkyLight hideOnOverlayClicked ref="simpleDialog">
                <div className="panel panel-default">
                <div className="panel-heading">Create student</div>
                <div className="panel-body">
                <form className="form">
                    <div className="col-md-4">
                        <input type="text" placeholder="Firstname" className="form-control"  name="firstname" onChange={this.handleChange}/>    
                    </div>
                    <div className="col-md-4">       
                        <input type="text" placeholder="Lastname" className="form-control" name="lastname" onChange={this.handleChange}/>
                    </div>
                    <div className="col-md-4">
                        <input type="text" placeholder="Email" className="form-control" name="email" onChange={this.handleChange}/>
                    </div>
                    <div className="col-md-2">
                        <button className="btn btn-primary" onClick={this.handleSubmit}>Save</button>   
                    </div>       
                </form>
                </div>      
                </div>
            </SkyLight>
            <div className="col-md-2">
                <button className="btn btn-primary" onClick={() => this.refs.simpleDialog.show()}>New student</button>
            </div>
          </div>   
        );
    }
}

class StudentUpdateForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {firstname: this.props.student.firstname, lastname: this.props.student.lastname, email: this.props.student.email};
        this.handleSubmit = this.handleSubmit.bind(this);   
        this.handleChange = this.handleChange.bind(this);     
    }

    handleChange(event) {
        this.setState(
            {[event.target.name]: event.target.value}
        );
    }    
    
    handleSubmit(event) {
        event.preventDefault();
        var updStudent = {link: this.props.student._links.self.href ,firstname: this.state.firstname, lastname: this.state.lastname, email: this.state.email};
        this.props.updateStudent(updStudent);   
        this.refs.editDialog.hide();         
    }
    
    render() {
        return (
          <div>
            <SkyLight hideOnOverlayClicked ref="editDialog">
                <div className="panel panel-default">
                <div className="panel-heading">Edit student</div>
                <div className="panel-body">
                <form className="form">
                    <div className="col-md-4">
                        <input type="text" placeholder="Firstname" className="form-control"  name="firstname" value={this.state.firstname} onChange={this.handleChange}/>    
                    </div>
                    <div className="col-md-4">       
                        <input type="text" placeholder="Lastname" className="form-control" name="lastname" value={this.state.lastname} onChange={this.handleChange}/>
                    </div>
                    <div className="col-md-4">
                        <input type="text" placeholder="Email" className="form-control" name="email" value={this.state.email} onChange={this.handleChange}/>
                    </div>
                    <div className="col-md-2">
                        <button className="btn btn-primary" onClick={this.handleSubmit}>Save</button>   
                    </div>       
                </form>
                </div>      
                </div>
            </SkyLight>
            <div>
                <button className="btn btn-primary btn-xs" onClick={() => this.refs.editDialog.show()}>Edit</button>
            </div>
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
                    <Register/>
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
            <h1>HEader</h1>
        )
    }

}

class Login extends Component {

    render() {
        return (
            <a href="http://localhost:8080/login">Login</a>
        )
    }

}

class Projects extends Component {
    render() {
        return (
            <h1>Projects</h1>
        )
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

ReactDOM.render(<App />, document.getElementById('root') );