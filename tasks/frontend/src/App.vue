<template>
  <b-container id="app">
    <navbar />
    <router-view />
  </b-container>
</template>

<script>
    import Navbar from '@/components/Navbar'

    export default {
        components: {
            'navbar': Navbar
        },
        data(){
            return {
                publicRoutes: ['home', 'auth']
            }
        },
        computed: {
            isPrivateRoute() {
                const index = this.publicRoutes.indexOf(this.$store.state.route.name)
                return index === -1
            }
        },
        methods: {
            async authenticate() {
                if(this.$store.getters.isLoggedIn) return
                try {
                    await this.$store.dispatch('authenticate')
                } catch {
                    if(this.isPrivateRoute)
                        this.$router.push({ name: 'home' })
                }
            },
        },
        created() {
            this.authenticate()
        },
        updated() {
            this.authenticate()
        }
    }
</script>