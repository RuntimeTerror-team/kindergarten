(this["webpackJsonpkindergarten-ui"]=this["webpackJsonpkindergarten-ui"]||[]).push([[0],{19:function(e,t,a){},63:function(e,t,a){},64:function(e,t,a){"use strict";a.r(t);var n=a(0),s=a(1),r=a.n(s),i=a(31),c=a.n(i),o=a(16),l=a(2),d=a(12),u=a(7),h=a(9),j=a(11),m=a(10),b=a(14),x=a.n(b),p=function(e){var t=e.username,a=e.password,s=e.usernameValidation,r=e.passwordValidation,i=e.areCredentialsIncorrect,c=e.onSubmit,o=e.onUsernameChange,l=e.onPasswordChange;return Object(n.jsx)("div",{className:"row",id:"loginForm",children:Object(n.jsxs)("div",{children:[Object(n.jsx)("h1",{id:"loginh1",children:"Prisijungimas"}),Object(n.jsxs)("form",{className:"form",onSubmit:c,children:[Object(n.jsxs)("div",{className:"form-group",children:[Object(n.jsx)("label",{htmlFor:"username",children:"Prisijungimo vardas: "}),Object(n.jsx)("input",{className:"form-control ".concat(s),id:"username",value:t,onChange:o,name:"username"}),Object(n.jsx)("div",{className:"invalid-feedback",children:"\u0160is laukas privalomas."})]}),Object(n.jsxs)("div",{className:"form-group",children:[Object(n.jsx)("label",{htmlFor:"password",className:"control-label",children:"Slapta\u017eodis: "}),Object(n.jsx)("input",{className:"form-control ".concat(r),type:"password",id:"password",value:a,onChange:l,name:"password"}),Object(n.jsx)("div",{className:"invalid-feedback",children:"\u0160is laukas privalomas."})]}),Object(n.jsx)("button",{className:"btn btn-primary mb-4",id:"loginButton",children:"Prisijungti"})]}),i&&Object(n.jsx)("div",{className:"alert alert-danger col-12",role:"alert",children:"Naudotojo vardas arba slapta\u017eodis neteisingas"})]})})},v="/kindergarten",O=r.a.createContext(null),g=function(e){Object(j.a)(a,e);var t=Object(m.a)(a);function a(){var e;return Object(u.a)(this,a),(e=t.call(this)).handleChange=function(t){var a=t.target,n=a.name,s=a.value;e.setState(Object(d.a)({},n,s)),""!==e.state.usernameValidation&&"username"===n&&e.setState({usernameValidation:""}),""!==e.state.passwordValidation&&"password"===n&&e.setState({passwordValidation:""}),e.state.areCredentialsIncorrect&&e.setState({areCredentialsIncorrect:!1})},e.resetState=function(){e.setState({username:""}),e.setState({password:""}),e.setState({usernameValidation:""}),e.setState({passwordValidation:""}),e.setState({incorrectCredentials:!1})},e.handleSubmit=function(t){t.preventDefault();var a="",n=t.target.username.value,s=t.target.password.value;e.doValidation(n,s),0!==n.trim().length&&0!==s.trim().length&&x.a.get("".concat(v,"/api/users/").concat(n)).then((function(t){var n=t.data.password;a=t.data.role,s===n?(e.context.userService.setCurrentUser(t.data.username),e.context.userService.setUserRole(a),e.context.userService.updateCurrentUser(),e.context.userService.updateUserRole(),e.resetState()):e.setState({areCredentialsIncorrect:!0})})).then((function(){"ADMIN"===e.context.userService.getUserRole()?e.props.history.push("/admin"):"EDUCATION_SPECIALIST"===e.context.userService.getUserRole()?e.props.history.push("/education-specialist"):"GUARDIAN"===e.context.userService.getUserRole()&&e.props.history.push("/guardian")})).catch((function(e){return console.log(e)}))},e.doValidation=function(t,a){0===t.trim().length&&e.setState({usernameValidation:"is-invalid"}),0===a.trim().length&&e.setState({passwordValidation:"is-invalid"})},e.state={username:"",password:"",usernameValidation:"",passwordValidation:"",areCredentialsIncorrect:!1},e}return Object(h.a)(a,[{key:"render",value:function(){return Object(n.jsxs)("div",{id:"loginPage",className:"justify-content-center align-items-center",children:[Object(n.jsx)("h1",{className:"text-center text-info pt-4",children:"Dar\u017eeli\u0173 informacin\u0117 sistema"}),Object(n.jsx)(p,{username:this.state.username,password:this.state.password,usernameValidation:this.state.usernameValidation,passwordValidation:this.state.passwordValidation,areCredentialsIncorrect:this.state.areCredentialsIncorrect,onSubmit:this.handleSubmit,onUsernameChange:this.handleChange,onPasswordChange:this.handleChange})]})}}]),a}(s.Component);g.contextType=O;var f=Object(l.f)(g),S=(a(19),function(e){var t=e.handleLogout,a=e.usersName;return Object(n.jsx)("div",{className:"container-fluid p-4",children:Object(n.jsxs)("div",{className:"container top-line",children:[Object(n.jsx)("p",{className:"lead",children:Object(n.jsx)("strong",{children:a})}),Object(n.jsx)("button",{className:"btn btn-info",onClick:t,children:"Atsijungti"})]})})}),C=a(33),N=a(34),U=function(e){var t=e.handleChange,a=e.handleSubmit,s=Object(N.a)(e,["handleChange","handleSubmit"]),r=s.firstname,i=s.lastname,c=s.firstnameFieldValidation,o=s.lastnameFieldValidation,l=s.createdUsername,d=s.isCreated;return Object(n.jsxs)("div",{className:"row justify-content-center align-items-center",children:[Object(n.jsxs)("div",{children:[Object(n.jsx)("h1",{className:"mb-4",children:"Paskyros suk\u016brimas"}),Object(n.jsxs)("form",{className:"form",onSubmit:a,children:[Object(n.jsxs)("div",{className:"form-group",children:[Object(n.jsx)("input",{type:"text",className:"form-control ".concat(c),id:"firstname",value:r,onChange:t,name:"firstname",placeholder:"Vardas"}),Object(n.jsx)("div",{className:"invalid-feedback",children:"Pra\u0161om u\u017epildyti vard\u0105."})]}),Object(n.jsxs)("div",{className:"form-group",children:[Object(n.jsx)("input",{type:"text",className:"form-control ".concat(o),id:"lastname",value:i,onChange:t,name:"lastname",placeholder:"Pavard\u0117"}),Object(n.jsx)("div",{className:"invalid-feedback",children:"Pra\u0161om u\u017epildyti pavard\u0119."})]}),Object(n.jsxs)("div",{className:"input-group mb-3",children:[Object(n.jsx)("div",{className:"input-group-prepend",children:Object(n.jsx)("label",{className:"input-group-text",htmlFor:"inputGroupSelect01",style:{backgroundColor:"#e3f2fd"},children:"Rol\u0117"})}),Object(n.jsxs)("select",{className:"custom-select",id:"inputGroupSelect01",name:"role",children:[Object(n.jsx)("option",{value:"GUARDIAN",defaultValue:!0,children:"T\u0117vas/glob\u0117jas"}),Object(n.jsx)("option",{value:"EDUCATION_SPECIALIST",children:"\u0160vietimo specialistas"})]})]}),Object(n.jsx)("button",{className:"btn btn-info float-right",children:"I\u0161saugoti"})]})]}),d&&l.length<=30&&Object(n.jsx)("div",{className:"alert alert-success mt-4",role:"alert",children:"Naudotojas sukurtas. Prisijungimo vardas ir slapta\u017eodis: ".concat(l)}),d&&l.length>30&&Object(n.jsx)("div",{className:"alert alert-success mt-4",role:"alert",children:l})]})},k=function(e){Object(j.a)(a,e);var t=Object(m.a)(a);function a(){var e;return Object(u.a)(this,a),(e=t.call(this)).handleChange=function(t){var a=t.target,n=a.name,s=a.value;""!==s&&/^[a-zA-Z\b]+$/.test(s)?e.setState(Object(d.a)({},n,s)):0===s.length&&e.setState(Object(d.a)({},n,"")),""!==e.state.firstnameFieldValidation&&"firstname"===n&&e.setState({firstnameFieldValidation:""}),""!==e.state.lastnameFieldValidation&&"lastname"===n&&e.setState({lastnameFieldValidation:""}),e.state.isCreated&&(e.setState({isCreated:!1}),e.setState({createdUsername:""}))},e.validate=function(t,a){0===t.trim().length&&e.setState({firstnameFieldValidation:"is-invalid"}),0===a.trim().length&&e.setState({lastnameFieldValidation:"is-invalid"})},e.handleSubmit=function(t){t.preventDefault(),e.state.isCreated&&(e.setState({isCreated:!1}),e.setState({createdUsername:""}));var a=t.target.firstname.value,n=t.target.lastname.value;e.validate(a,n),0!==a.trim().length&&0!==n.trim().length&&(x.a.post("".concat(v,"/api/users/admin"),{firstName:a,lastName:n,role:t.target.role.value}).then((function(t){e.setState({isCreated:!0}),e.setState({createdUsername:t.data})})).catch((function(e){return console.log(e)})),e.setState({firstname:""}),e.setState({lastname:""}),e.setState({role:""}))},e.state={firstname:"",lastname:"",role:"",firstnameFieldValidation:"",lastnameFieldValidation:"",isCreated:!1,createdUsername:""},e}return Object(h.a)(a,[{key:"render",value:function(){return Object(n.jsx)("div",{children:Object(n.jsx)(U,Object(C.a)({handleSubmit:this.handleSubmit,handleChange:this.handleChange},this.state))})}}]),a}(s.Component);k.contextType=O;var w=k,y=function(e){var t=e.handleLogout,a=e.handleUserChoice,s=e.choice;return Object(n.jsxs)("div",{children:[Object(n.jsx)(S,{handleLogout:t,usersName:"Administratoriaus paskyra"}),Object(n.jsx)("div",{className:"container p-4",children:Object(n.jsxs)("div",{className:"row",children:[Object(n.jsxs)("div",{className:"admin-actions col-4",children:[Object(n.jsx)("button",{className:"btn btn-info mb-2 w-100",onClick:a,name:"adminUsers",children:"Paskyr\u0173 administravimas"}),Object(n.jsx)("button",{className:"btn btn-info mb-2 w-100",onClick:a,name:"2",children:"Ka\u017ekas dar"}),Object(n.jsx)("button",{className:"btn btn-info mb-2 w-100",onClick:a,name:"3",children:"Ir dar ka\u017ekas"})]}),Object(n.jsxs)("div",{className:"admin-action-placeholder col-8",children:["greeting"===s&&Object(n.jsx)("h1",{children:"Sveiki prisijung\u0119 \u012f dar\u017eeli\u0173 informacin\u0119 sistem\u0105"}),"adminUsers"===s&&Object(n.jsx)(w,{}),"2"===s&&Object(n.jsx)("h1",{children:"Ka\u017ekas dar"}),"3"===s&&Object(n.jsx)("h1",{children:"Ir dar ka\u017ekas"})]})]})})]})},V=function(e){Object(j.a)(a,e);var t=Object(m.a)(a);function a(){var e;return Object(u.a)(this,a),(e=t.call(this)).handleUserChoice=function(t){e.setState({choice:t.target.name})},e.handleLogout=function(){e.context.userService.setCurrentUser(""),e.context.userService.setUserRole(""),e.context.userService.updateCurrentUser(),e.context.userService.updateUserRole(),e.props.history.push("/")},e.state={choice:"greeting",currentUserFirstame:"",currentUserLastname:""},e}return Object(h.a)(a,[{key:"render",value:function(){return"ADMIN"===this.context.userService.getUserRole()?Object(n.jsx)("div",{className:"pagesBackgorund",children:Object(n.jsx)(y,{handleUserChoice:this.handleUserChoice,handleLogout:this.handleLogout,choice:this.state.choice})}):Object(n.jsx)("h1",{children:"Access denied"})}}]),a}(s.Component);V.contextType=O;var I=V,L=function(e){var t=e.handleLogout,a=e.handleUserChoice,s=e.choice;return Object(n.jsxs)("div",{children:[Object(n.jsx)(S,{handleLogout:t,usersName:"\u0160vietimo specialistas"}),Object(n.jsx)("div",{className:"container p-4",children:Object(n.jsxs)("div",{className:"row",children:[Object(n.jsxs)("div",{className:"admin-actions col-4",children:[Object(n.jsx)("button",{className:"btn btn-info mb-2 w-100",onClick:a,name:"1",children:"Ka\u017ekas"}),Object(n.jsx)("button",{className:"btn btn-info mb-2 w-100",onClick:a,name:"2",children:"Ka\u017ekas dar"}),Object(n.jsx)("button",{className:"btn btn-info mb-2 w-100",onClick:a,name:"3",children:"Ir dar ka\u017ekas"})]}),Object(n.jsxs)("div",{className:"admin-action-placeholder col-8",children:["greeting"===s&&Object(n.jsx)("h1",{children:"Sveiki prisijung\u0119 \u012f dar\u017eeli\u0173 informacin\u0119 sistem\u0105"}),"1"===s&&Object(n.jsx)("h1",{children:"Ka\u017ekas"}),"2"===s&&Object(n.jsx)("h1",{children:"Ka\u017ekas dar"}),"3"===s&&Object(n.jsx)("h1",{children:"Ir dar ka\u017ekas"})]})]})})]})},R=function(e){Object(j.a)(a,e);var t=Object(m.a)(a);function a(){var e;return Object(u.a)(this,a),(e=t.call(this)).handleUserChoice=function(t){e.setState({choice:t.target.name})},e.handleLogout=function(){e.context.userService.setCurrentUser(""),e.context.userService.setUserRole(""),e.context.userService.updateCurrentUser(),e.context.userService.updateUserRole(),e.props.history.push("/")},e.state={choice:"greeting"},e}return Object(h.a)(a,[{key:"render",value:function(){return"EDUCATION_SPECIALIST"===this.context.userService.getUserRole()?Object(n.jsx)(L,{handleUserChoice:this.handleUserChoice,handleLogout:this.handleLogout,choice:this.state.choice}):Object(n.jsx)("h1",{children:"Access denied"})}}]),a}(s.Component);R.contextType=O;var A=R,F=function(e){var t=e.handleLogout,a=e.handleUserChoice,s=e.choice,r=e.currentUserFirstname,i=e.currentUserLastname;return Object(n.jsxs)("div",{children:[Object(n.jsx)(S,{handleLogout:t,usersName:"".concat(r," ").concat(i)}),Object(n.jsx)("div",{className:"container p-4",children:Object(n.jsxs)("div",{className:"row",children:[Object(n.jsxs)("div",{className:"admin-actions col-4",children:[Object(n.jsx)("button",{className:"btn btn-info mb-2 w-100",onClick:a,name:"1",children:"Ka\u017ekas"}),Object(n.jsx)("button",{className:"btn btn-info mb-2 w-100",onClick:a,name:"2",children:"Ka\u017ekas dar"}),Object(n.jsx)("button",{className:"btn btn-info mb-2 w-100",onClick:a,name:"3",children:"Ir dar ka\u017ekas"})]}),Object(n.jsxs)("div",{className:"admin-action-placeholder col-8",children:["greeting"===s&&Object(n.jsx)("h1",{children:"Sveiki prisijung\u0119 \u012f dar\u017eeli\u0173 informacin\u0119 sistem\u0105"}),"1"===s&&Object(n.jsx)("h1",{children:"Ka\u017ekas"}),"2"===s&&Object(n.jsx)("h1",{children:"Ka\u017ekas dar"}),"3"===s&&Object(n.jsx)("h1",{children:"Ir dar ka\u017ekas"})]})]})})]})},P=function(e){Object(j.a)(a,e);var t=Object(m.a)(a);function a(){var e;return Object(u.a)(this,a),(e=t.call(this)).componentDidMount=function(){var t=e.context.userService.getCurrentUser();x.a.get("".concat(v,"/api/users/").concat(t)).then((function(t){e.setState({currentUserFirstname:t.data.firstName}),e.setState({currentUserLastname:t.data.lastName})})).catch((function(e){return console.log(e)}))},e.handleUserChoice=function(t){e.setState({choice:t.target.name})},e.handleLogout=function(){e.context.userService.setCurrentUser(""),e.context.userService.setUserRole(""),e.context.userService.updateCurrentUser(),e.context.userService.updateUserRole(),e.props.history.push("/")},e.state={choice:"greeting",currentUserFirstname:"",currentUserLastname:""},e}return Object(h.a)(a,[{key:"render",value:function(){return"GUARDIAN"===this.context.userService.getUserRole()?Object(n.jsx)("div",{children:Object(n.jsx)(F,{handleUserChoice:this.handleUserChoice,handleLogout:this.handleLogout,choice:this.state.choice,currentUserFirstname:this.state.currentUserFirstname,currentUserLastname:this.state.currentUserLastname})}):Object(n.jsx)("h1",{children:"Access denied"})}}]),a}(s.Component);P.contextType=O;var T=P,D=function(e){return Object(n.jsx)("div",{className:"container",children:Object(n.jsxs)("div",{className:"m-5",children:[Object(n.jsx)("h1",{children:"Tokio adreso n\u0117ra"}),Object(n.jsx)("button",{className:"btn btn-primary ml-2 mt-3",onClick:function(){return e.history.push("/")},children:"Eiti \u012f prisijungimo puslap\u012f"})]})})};var K=function(e){return Object(n.jsxs)("div",{children:[Object(n.jsxs)(l.c,{children:[Object(n.jsx)(l.a,{exact:!0,path:"/",component:f}),Object(n.jsx)(l.a,{exact:!0,path:"/admin",component:I}),Object(n.jsx)(l.a,{exact:!0,path:"/education-specialist",component:A}),Object(n.jsx)(l.a,{exact:!0,path:"/guardian",component:T}),Object(n.jsx)(l.a,{path:"*",component:D}),Object(n.jsx)(l.a,{component:D})]}),e.children]})},_=function e(){var t=this;Object(u.a)(this,e),this.getCurrentUser=function(){return t._currentUser},this.setCurrentUser=function(e){t._currentUser=e},this.getUserRole=function(){return t._userRole},this.setUserRole=function(e){t._userRole=e},this.updateCurrentUser=function(){},this.updateUserRole=function(){},this._currentUser="",this._userRole=""};a(62),a(63);document.title="Kindergarten App";var E=new _;c.a.render(Object(n.jsx)(r.a.StrictMode,{children:Object(n.jsx)(o.a,{basename:"/kindergarten",children:Object(n.jsx)(O.Provider,{value:{userService:E},children:Object(n.jsx)(K,{})})})}),document.getElementById("root"))}},[[64,1,2]]]);
//# sourceMappingURL=main.695af3ff.chunk.js.map