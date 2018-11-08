import { instance } from '../connector'

const USER_ENDPOINT = `/user`
const LOGIN_ENDPOINT = `/login`
const REGISTER_ENDPOINT = `/register`

const authModule = {
    state: {
        user: null,
    },
    mutations: {
        setUser(state, user) {
            state.user = user
        }
    },
    getters: {
        user: (state) => state.user,
        isLoggedIn: (state) => !!state.user,
    },
    actions: {
        async authenticate({ commit }) {
            const token = localStorage.getItem('token')
            instance.defaults.headers.common['Authorization'] = `Bearer ${token}`
            const res = await instance.get(USER_ENDPOINT)
            commit('setUser', res.data.id)
        },
        async login({ commit }, { username, password }) {
            const res = await instance.post(LOGIN_ENDPOINT, {username, password})
            const {id, token} = res.data
            localStorage.setItem('token', token)
            instance.defaults.headers.common['Authorization'] = `Bearer ${token}`
            commit('setUser', id)
        },
        async register({ commit }, { email, username, password }) {
            const res = await instance.post(REGISTER_ENDPOINT, { email, username, password })
            const { id, token } = res.data
            localStorage.setItem('token', token)
            instance.defaults.headers.common['Authorization'] = `Bearer ${token}`
            commit('setUser', id)
        },
        async logout({ commit }) {
            localStorage.removeItem('token')
            commit('setUser')
        }
    }
}

export default authModule