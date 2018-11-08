import axios from 'axios'

const BASE_URL = 'http://localhost:9000'
const USER_ENDPOINT = `/user`

const instance = axios.create({
    baseURL: BASE_URL
})

const profileModule = {
    namespaced: true,
    state: {
        details: null,
        pending: false,
    },
    mutations: {
        setUser(state, user) {
            state.details = user
        },
        setPending(state, pending) {
            state.pending = pending
        }
    },
    getters: {
        user: (state) => state.details,
        isPending: (state) => state.pending
    },
    actions: {
        async fetch({ commit, dispatch }) {
            const user = await dispatch('sendRequest', instance.get(USER_ENDPOINT))
            commit('setUser', user)
        },
        async update({ commit, dispatch }, { email, username, password }) {
            const user = await dispatch('sendRequest', instance.post(USER_ENDPOINT, { email, username, password }))
            commit('setUser', user)
        },
        async delete({ commit, dispatch }) {
            await dispatch('sendRequest', instance.delete(USER_ENDPOINT))
            commit('setUser')
            dispatch('logout', null, { root: true })
        },
        async sendRequest({ commit }, request) {
            commit('setPending', true)
            let response
            try {
                response = await request
            } finally {
                commit('setPending', false)
            }
            return response.data
        }
    }
}

export default profileModule