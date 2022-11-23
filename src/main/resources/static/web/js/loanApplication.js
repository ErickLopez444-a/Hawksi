Vue.createApp({
    data() {
        return {
            loantype:18,
            borrowerAccount:"",
            amount:0,
            nombre: "",
            accounts: [],
            payments:[],
            loans:{},

        }
    },
    created() {
        axios.get("/api/clients/current")
        .then(datos => {
            this.accounts = datos.data.accounts
        }),
        axios.get("/api/loans")
        .then(datos => {

            
       
            this.loans= datos.data
            this.loans = this.loans.sort((a, b) => a.id - b.id)
        
        
            

        })

    },


    methods: {
        signOut(){
            axios.post('/api/logout').then(response =>{ 
                window.location.href = "/web/index.html"
            })
        },
        applyLoan(){
            Swal.fire({
                title:` Please Confirm you want the loan ${this.loantype} and pay it in ${this.payments} payments? ` ,
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: '#014377',
                cancelButtonColor: '#ff0000',
                confirmButtonText: 'Apply Loan'
            })

            .then((result) => {
                if (result.isConfirmed) {
                
                    axios.post('/api/loans',{id:this.loantype,accountNumber:this.borrowerAccount,amount:this.amount,payments:this.payments} //

                    )

                        .then(() => {
                            Swal.fire({
                                title: "Your Loan was created successfully!",
                                text: "You will be redirected to your account in a few seconds",
                                icon: "success",
                                timer: 2000

                            })
                            .then(response =>  window.location.href = "/web/accounts.html")

                        })

                        .catch((error) => {
                            console.log(error.response.data)
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
        }
        
        
   


    },



}).mount('#app')
const toggleButton = document.getElementsByClassName('toggle-button')[0]
const navbarLinks = document.getElementsByClassName('navbar-links')[0]

toggleButton.addEventListener('click', () => {
  navbarLinks.classList.toggle('active')
})