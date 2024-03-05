import http from "../htpp-common"

class MovieDataService {
    getAllMovies(page, size, sortBy, title, yearFrom, yearTo) {
        const url = `/movies?page=${page}&size=${size}&sortBy=${sortBy}&title=${title}&yearFrom=${yearFrom}&yearTo=${yearTo}`;
        return http.get(url);
    }

    saveMovie(data) {
        return http.post("/movies", data);
    }

    getById(id) {
        return http.get(`/movies/${id}`);
    }

    getAllReviews(page, size, sortBy, title, yearFrom, yearTo) {
        const url = `/reviews?page=${page}&size=${size}&sortBy=${sortBy}&title=${title}&yearFrom=${yearFrom}&yearTo=${yearTo}`;
        return http.get(url);
    }

    saveReview(id, data) {
        return http.post(`/movies/${id}/review`, data);
    }

    saveRating(id, data) {
        return http.post(`/movies/${id}/rate`, data)
    }
}

export default new MovieDataService();