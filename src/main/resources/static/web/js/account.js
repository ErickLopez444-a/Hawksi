/* const app = Vue.createApp({
    data(){
        return{
            nombre: "",
            transactions: [],
            accounts: [],
            sortedTransactions: [],
            loans:[],
        }
    },
    created() {
        const urlParams = new URLSearchParams(window.location.search);
        const myParam = urlParams.get('id');
        console.log(myParam)

        axios.get("api/accounts/1")
            .then(response => {
            })
  
    },
    methods: {


    },
    computed:{
    },
})
app.mount('#app') */
Vue.createApp({
    data() {
        return {
            nombre: "",
            datos:[],
            transactions: [],
            accounts: [],
            sortedTransactions: [],
            loans:[],
            account:"",
            accountBalance:""

            



        }
    },
    created() {
        const urlParams = new URLSearchParams(window.location.search);
        const myParam = urlParams.get('id');
        axios.get(`/api/accounts/${myParam}`)
        .then(response => {
            this.datos= response.data
            console.log(this.datos)
            this.account= this.datos.number
            this.accountBalance=this.datos.balance
            this.transactions=this.datos.transaction
            this.transactions.sort((a, b) => b.id-a.id)
    
            

        })


    },
    methods: {

        signOut(){
            axios.post('/api/logout').then(response =>{ 
                window.location.href = "/web/index.html"
            })
        },
        modificarSaldo(saldo){
            return new Intl.NumberFormat("en-US", {style:"currency",currency:"USD"}).format(saldo);
        },
        datemodified(date){
            return new Date(date).toLocaleDateString('en-US', {weekday: "long", year:"numeric", month:"short", day:"numeric", hour:"2-digit", minute:"2-digit"})
        },
    
        
        
    },





}).mount('#app')
const toggleButton = document.getElementsByClassName('toggle-button')[0]
const navbarLinks = document.getElementsByClassName('navbar-links')[0]

toggleButton.addEventListener('click', () => {
  navbarLinks.classList.toggle('active')
})