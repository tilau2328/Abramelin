<template>
    <div>
        <div v-if="isPending || user == null">
            <h1>Loading...</h1>
        </div>
        <div v-else-if="!edit">
            <h1>Profile: {{ user.username }}</h1>
            <p>Email: {{ user.email }}</p>
            <b-button
                @click="remove"
                variant="danger">
                Delete
            </b-button>
            <b-button
                @click="edit=true"
                variant="info">
                Edit
            </b-button>
        </div>
        <div v-else>
            <b-form @submit.prevent='update()' @reset='reset()'>
                <b-form-group>
                    <b-form-input
                        type="text"
                        v-model="email"
                        class="form-control"
                        placeholder="Email">
                    </b-form-input>
                </b-form-group>
                <b-form-group>
                    <b-form-input
                        type="text"
                        v-model="username"
                        class="form-control"
                        placeholder="Username">
                    </b-form-input>
                </b-form-group>
                <b-form-group>
                    <b-form-input
                        type="password"
                        v-model="password"
                        placeholder="Password">
                    </b-form-input>
                </b-form-group>
                <b-form-group>
                    <b-button
                        @click="cancel"
                        variant="secondary">
                        Cancel
                    </b-button>
                    <b-button
                        type="reset"
                        variant="info">
                        Reset
                    </b-button>
                    <b-button
                        type="submit"
                        variant="primary">
                        Submit
                    </b-button>
                </b-form-group>
            </b-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Profile",
        data() {
            return {
                email: '',
                username: '',
                password: '',
                edit: false
            }
        },
        computed: {
            user() {
                return this.$store.getters['profile/user']
            },
            isPending() {
                return this.$store.getters['profile/isPending']
            }
        },
        methods: {
            reset() {
                this.email = ''
                this.username = ''
                this.password = ''
            },
            cancel() {
                this.edit = false
                this.reset()
            },
            async update(){
                const email = this.email === "" ? null : this.email
                const username = this.username === "" ? null : this.username
                const password = this.password === "" ? null : this.password
                await this.$store.dispatch('profile/update', { email, username, password })
                this.cancel()
            },
            async remove(){
                const username = prompt("Confirm your username please")
                if(username !== this.user.username) return
                await this.$store.dispatch('profile/delete')
                this.$router.push({ name: 'home' })
            }
        },
        async created() {
            await this.$store.dispatch('profile/fetch')
        }
    }
</script>

<style scoped>

</style>