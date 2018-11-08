<template>
    <b-col id="auth" class="col-xs-12 col-md-8 offset-md-2 col-lg-6 offset-lg-3">
        <h1>{{ login ? 'Login' : 'Register' }}</h1>
        <b-form @submit.prevent='confirm()' @reset='reset()'>
            <b-form-group v-if="!login">
                <b-form-input
                    required
                    type="text"
                    v-model="email"
                    class="form-control"
                    placeholder="Email">
                </b-form-input>
            </b-form-group>
            <b-form-group>
                <b-form-input
                    required
                    type="text"
                    v-model="username"
                    class="form-control"
                    placeholder="Username">
                </b-form-input>
            </b-form-group>
            <b-form-group>
                <b-form-input
                    required
                    type="password"
                    v-model="password"
                    placeholder="Password">
                </b-form-input>
            </b-form-group>
            <b-form-group>
                <b-button
                        variant="link"
                        @click="login = !login"
                >
                    {{login ? 'Need to create an account?' : 'Already have an account?'}}
                </b-button>
                <b-button-group class="float-right">
                    <b-button
                            type="reset"
                            variant="danger"
                            :disabled="email===''&&username===''&&password===''"
                    >
                        Reset
                    </b-button>
                    <b-button
                            type="submit"
                            variant="primary"
                            class="float-right"
                    >
                        {{login ? 'Login' : 'Register'}}
                    </b-button>
                </b-button-group>
            </b-form-group>
        </b-form>
    </b-col>
</template>

<script>
    export default {
        name: 'auth',
        data() {
            return {
                email: '',
                login: true,
                username: '',
                password: ''
            }
        },
        methods: {
            async confirm() {
                const { email, username, password } = this.$data
                let promise
                if(this.login) {
                    if(username === "" || password === "") return
                    promise = this.$store.dispatch('login', {username, password})
                } else {
                    if(email === "" || username === "" || password === "") return
                    promise = this.$store.dispatch('register', {email, username, password})
                }
                await promise
                this.$router.push({ name: 'home' })
            },
            reset() {
                this.email = ''
                this.username = ''
                this.password = ''
            }
        },
        created() {
            if(this.$store.getters.isLoggedIn) {
                this.$router.go(-1)
            }
        }
    }
</script>
