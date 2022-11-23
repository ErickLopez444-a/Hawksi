Vue.createApp({
    data() {
        return {
            datos: [],// checar esto
            cliente: [],
            cuentas: [],
            cards:[],
            firstName:"",
            lastName:"",

        }
    },
    created() {
        axios.get("/api/clients/current") // 404 not found,405m
        .then(response => {
            this.datos = response // checar esto
            this.cliente = this.datos.data
            this.cards = this.cliente.cards
            this.loans = this.cliente.loans
            console.log(this.cards)
        })

    },


    methods: {
        signOut(){
            axios.post('/api/logout').then(response =>{ 
                window.location.href = "/web/index.html"
            })
        }
   


    },



}).mount('#app')
const toggleButton = document.getElementsByClassName('toggle-button')[0]
const navbarLinks = document.getElementsByClassName('navbar-links')[0]

toggleButton.addEventListener('click', () => {
  navbarLinks.classList.toggle('active')
})
