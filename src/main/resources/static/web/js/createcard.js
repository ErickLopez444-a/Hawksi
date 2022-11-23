
Vue.createApp({
    data() {
        return {
            cardType:"",
            cardColor:"",


        }
    },

    created() {
        axios.get("/api/clients/current")
        .then(datos =>{
            this.accounts = datos.data.accounts
            this.nombre = datos.data.firstName
        } )





    },

    methods: {
/*         newCard(){
            axios.post('/api/clients/current/cards', `type=${this.cardType}&color=${this.cardColor}`)
            .then(response => {
                
                window.location.href = "/web/cards.html"})
        .catch(function (error){
            if(error.response.data=="You can not have more than 3 Credit cards")
            return Swal.fire({
                confirmButtonColor:'#3032d6',
                icon: 'error',
                title: 'We Are Sorry...',
                text: 'You Have Reached Your Credit Card Limit',
              })
              if(error.response.data=="You can not have more than 3 debit cards")
              return Swal.fire({
                  confirmButtonColor:'#3032d6',
                  icon: 'error',
                  title: 'We Are Sorry...',
                  text: 'You Have Reached Your Debit Card Limit',
                })
        })

                

    }, */
    newCard() {

        Swal.fire({
                title: "Are you sure you want to Request this New Card?",
                text:  ` with the following type=${this.cardType}& Card color=${this.cardColor}`,
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: '#014377',
                cancelButtonColor: '#ff0000',
                confirmButtonText: 'Request Now'
            })

            .then((result) => {
                if (result.isConfirmed) {

                    axios.post('/api/clients/current/cards', `type=${this.cardType}&color=${this.cardColor}`, {
                        })
                        .then(() => {
                            Swal.fire({
                                title: 'Your Card Was  Succsesfully Created!',
                                text: "The Card you requested was aprroved youll be returned to your Cards!",
                                icon: "success",
                                timer: 2500

                            })
                            .then(response =>  window.location.href = "/web/cards.html")

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
   
    
    
    


    },

}).mount('#app')
const toggleButton = document.getElementsByClassName('toggle-button')[0]
const navbarLinks = document.getElementsByClassName('navbar-links')[0]

toggleButton.addEventListener('click', () => {
  navbarLinks.classList.toggle('active')
})
