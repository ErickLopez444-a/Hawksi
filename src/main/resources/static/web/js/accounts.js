const app = Vue.createApp({
    data(){
        return{
            datos: [],// checar esto
            cliente: [],
            cuentas: [],
            loans:[],
            firstName:"",
            lastName:"",
        }
    },
    created() {
        this.loadData();
    },
    methods: {
        loadData() {
            axios.get("/api/clients/current") // 404 not found,405m
                .then(response => {
                    this.datos = response // checar esto
                    this.cliente = this.datos.data
                    this.cuentas = this.cliente.accounts
                    this.loans = this.cliente.loans
                    console.log(this.loans)
                })
        },
        signOut(){
            axios.post('/api/logout').then(response =>{ 
                window.location.href = "/web/index.html"
            })
        },

        newAccount() {

            Swal.fire({
                    title: "Are you sure you want to Create a New Account?",
                    text: "Please confirm the Creation of the New Account",
                    icon: "warning",
                    text:"For deleting an account youll have to contact our suport sistem or go to your Nearest Hawks Banking",
                    showCancelButton: true,
                    confirmButtonColor: '#014377',
                    cancelButtonColor: '#ff0000',
                    confirmButtonText: 'Confirm'
                })
    
                .then((result) => {
                    if (result.isConfirmed) {
    
                        axios.post('/api/clients/current/accounts', {
                            })
                            .then(() => {
                                Swal.fire({
                                    title: 'Your Account Was Creted Succsesfully!',
                                    text: "You will see your New Account in a moment",
                                    icon: "success",
                                    timer: 2500
    
                                })
                                .then(response =>  window.location.href = "/web/accounts.html")
    
                            })
    
                            .catch( function(error) {
                                Swal.fire({
                                    icon: 'error',
                                    text: error.response.data,
    
                                })
                            })
    
    
                    }
    
    
                });
            },
        modificarSaldo(saldo){
            return new Intl.NumberFormat("en-US", {style:"currency",currency:"USD"}).format(saldo);
        },
        dateModified(date){
            return new Date(date).toLocaleDateString('en-US', {weekday: "long", year:"numeric", month:"short", day:"numeric"})
        },
        
        
    },
    
    computed:{
    },
})
app.mount('#app')
const toggleButton = document.getElementsByClassName('toggle-button')[0]
const navbarLinks = document.getElementsByClassName('navbar-links')[0]

toggleButton.addEventListener('click', () => {
  navbarLinks.classList.toggle('active')
})
