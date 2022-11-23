
const app = Vue.createApp({
  data(){
      return{
        email: "",
        password: "",
        newEmail: "",
            newName: "",
            newLastname: "",
            newPassword: "",

      }
  },
  created() {
  },
  methods: {
    signIn() {
      axios.post('/api/login', `email=${this.email}&password=${this.password}`
      ).then(response => {
          window.location.href = "/web/accounts.html"
      }).catch(function (error){
        Swal.fire('Wrong password or username')
    })
  },
  signUp() {
    axios.post('/api/clients',`firstName=${this.newName}&lastName=${this.newLastname}&email=${this.newEmail}&password=${this.newPassword}`)
    .then(response =>axios.post("/api/login", `email=${this.newEmail}&password=${this.newPassword}`))
    .then(response => { 
      window.location.href = "/web/accounts.html" })
    .catch(function(error){
      if(error.response.data=="Missing firstname"){
        return Swal.fire({
          confirmButtonColor:'#3032d6',
          icon: 'error',
          title: 'Oops...',
          text: 'First Name field is missing',
        })
      }
      if(error.response.data=="Missing Last Name"){
        return Swal.fire({
          confirmButtonColor:'#3032d6',
          icon: 'error',
          title: 'Oops...',
          text: 'Last Name field is missing',
        })
      }
      if(error.response.data=="Missing email"){
        return Swal.fire({
          confirmButtonColor:'#3032d6',
          icon: 'error',
          title: 'Oops...',
          text: 'email field is missing',
        })
      }
      if(error.response.data=="password"){
        return Swal.fire({
          confirmButtonColor:'#3032d6',
          icon: 'error',
          title: 'Oops...',
          text: 'password field is missing',
        })
      }
    })

    
  
}


  },

})
app.mount('#app')
const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", () => {
  container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
});
