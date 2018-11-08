<template>
    <b-navbar toggleable="md" >
        <b-navbar-brand :to="{ name: 'home' }">Abramelin</b-navbar-brand>
        <b-navbar-toggle target="nav_collapse" ></b-navbar-toggle>
        <b-collapse is-nav id="nav_collapse">
            <b-navbar-nav class="ml-auto">
                <b-nav-item v-if="user == null && !isAuthRoute" :to="{ name: 'auth' }">Login</b-nav-item>
                <b-nav-item-dropdown v-if="!!user" right>
                    <template slot="button-content">
                        User
                    </template>
                    <b-dropdown-item :to="{ name: 'profile' }">Profile</b-dropdown-item>
                    <b-dropdown-item href="#" @click="logout()">Logout</b-dropdown-item>
                </b-nav-item-dropdown>
            </b-navbar-nav>
        </b-collapse>
    </b-navbar>
</template>

<script>
export default {
    name: 'Navbar',
    computed: {
        user() {
            return this.$store.getters.user
        },
        isAuthRoute() {
            return this.$store.state.route &&
                this.$store.state.route.name === 'auth'
        }
    },
    methods: {
        logout() {
            this.$store.dispatch('logout')
                .then(() => this.$router.push({ name: 'home' }))
        }
    }
}
</script>