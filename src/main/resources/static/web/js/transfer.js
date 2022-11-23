Vue.createApp({
    data() {
        return {
            nombre: "",
            accounts: [],
            amount: "",
            description: "",
            accountFrom: "",
            accountTo: "",
            accountType:"myAccount",
            myAccount:"",
            otraCuenta:"",


        }
    },

    created() {
        axios.get("/api/clients/current")
            .then(datos => {
                this.accounts = datos.data.accounts
                this.nombre = datos.data.firstName

            })

    },

    methods: {
        signOut() {
            axios.post('/api/logout').then(response => {
                window.location.href = "/web/index.html"
            })
        },
     
        sendTransfer() {

            Swal.fire({
                    title: "Are you sure you want to make this transfer?",
                    text: "Please Confirm the transfer",
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: '#014377',
                    cancelButtonColor: '#ff0000',
                    confirmButtonText: 'Transfer now'
                })

                .then((result) => {
                    if (result.isConfirmed) {

                        axios.post('/api/transactions', `amount=${this.amount}&description=${this.description}&originAccountNumber=${this.accountFrom}&destinationAccountNumber=${this.accountTo}`, {
                            })
                            .then(() => {
                                Swal.fire({
                                    title: 'Transaction Succsesful!',
                                    text: "The Transaction was succsesful youll be returned to your accounts!",
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





        }
        }).mount('#app')
        const toggleButton = document.getElementsByClassName('toggle-button')[0]
const navbarLinks = document.getElementsByClassName('navbar-links')[0]

toggleButton.addEventListener('click', () => {
  navbarLinks.classList.toggle('active')
})