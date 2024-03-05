import {createRouter, createWebHistory} from 'vue-router'

const routes = [
    {
        path: '/movies',
        alias: '/',
        name: 'movies',
        component: () => import('./components/MoviesList')
    },
    {
        path: '/movies/:id',
        name: 'movie-details',
        component: () => import('./components/MovieDetails')
    },
    {
        path: '/reviews',
        name: 'reviews',
        component: () => import('./components/ReviewsList')
    },
    {
        path: '/movies/:id/reviews/create',
        name: 'reviews-form',
        component: () => import('./components/ReviewsForm')
    },
    {
        path: '/movies/:id/ratings/create',
        name: 'ratings-form',
        component: () => import('./components/RatingsForm')
    },
    {
        path: '/create',
        name: 'create-movie',
        component: () => import('./components/MovieForm')
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router