const app = Vue.createApp({
    data() {
        return {
            loanName: "",
            maxAmount: 0,
            payments: [],
            payment: '',
            interest: 0,
        }
    },
    mounted() {

    },
    methods: {
        addPayment() {
        
            parseInt(this.payment)
            this.payments.push(parseInt(this.payment))
            this.payment = ''
        },
        signOut() {
            axios.post('/api/logout')
                .then(() => window.location.href = "/web/index.html")
        },
  
        newLoan() {
            axios.post("/api/post/loan", { name: this.loanName, maxAmount: parseInt(this.maxAmount), payments: this.payments, interest: parseInt(this.interest) })
            .then(() => window.location.href = "/api/loans")
             
        },
    },


    computed: {
        paymentsSorted() {
            return this.payments.sort((a, b) => a - b)
        }
    }
}).mount('#app')