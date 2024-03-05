<template>
  <div class="d-flex flex-column min-vh-100">
    <div class="d-flex justify-content-between align-items-center m-4">
      <div class="d-flex justify-content-between align-items-center">
        <router-link to="/create" class="btn">Add movie</router-link>
        <router-link to="/reviews" class="btn ml-3">Reviews</router-link>
      </div>
      <div class="d-flex justify-content-center">
        <label for="sort">Sort by:</label>
        <select v-model="sortBy" @change="getMovies(0, this.pageSize)" id="sort" class="form-select ml-2">
          <option value="genre">Genre</option>
          <option value="title">Title</option>
          <option value="year">Year</option>
        </select>
      </div>
      <div class="d-flex justify-content-end">
        <form class="form-horizontal">
          <label for="title">Title</label>
          <input type="text" id="title" name="title" v-model="title">
          <label for="yearFrom">Year From</label>
          <input type="number" id="yearFrom" name="yearFrom" v-model="yearFrom">
          <label for="yearTo">Year To</label>
          <input type="number" id="yearTo" name="yearTo" v-model="yearTo">
          <button @click.prevent="getMovies(0, this.pageSize)" class="btn ml-2">Filter</button>
        </form>
      </div>
    </div>
    <div class="card-columns ml-4 mr-4">
      <div v-for="movie in movies" :key="movie.id" class="card mb-4">
        <div class="card-body">
          <div class="card-text">
            <div class="d-flex justify-content-between align-items-center">
              <div class="d-flex justify-content-start align-items-center">
                <h2 class="title">{{ movie.title }}</h2>
                <h5 class="pl-2">({{ movie.year }})</h5>
              </div>
              <h5><span style="color: rgba(0, 0, 255, .4)">★</span>{{ movie.averageRating }}</h5>
            </div>
            <h6>{{ movie.genre }}</h6>
            <h5 class="desc">{{ movie.description }}</h5>
          </div>
          <div class="d-flex justify-content-between pt-4">
            <router-link :to="{ name: 'movie-details', params: {id: movie.id} }" class="btn">Movie details</router-link>
            <router-link :to="{ name: 'reviews-form', params: {id: movie.id} }" class="btn">Add review</router-link>
            <router-link :to="{ name: 'ratings-form', params: {id: movie.id} }" class="btn">Add rating</router-link>
          </div>
        </div>
      </div>
    </div>
    <div v-if="totalItems > pageSize" class="d-flex align-items-center justify-content-center mt-auto mb-3">
      <button @click="previousPage" :disabled="currentPage === 1" class="btn">Previous</button>
      <span class="pl-2 pr-2">Page {{ currentPage }} of {{ totalPages }}</span>
      <button @click="nextPage" :disabled="currentPage === totalPages" class="btn">Next</button>
    </div>
  </div>
</template>

<script>
import MovieDataService from "@/services/MovieDataService";

export default {
  name: 'HelloWorld',
  data() {
    return {
      movies: [],
      title: "",
      yearFrom: "",
      yearTo: "",
      currentPage: 1,
      pageSize: 6,
      totalItems: 0,
      sortBy: 'year'
    }
  },
  methods: {
    getMovies(page, size) {
      let yearFrom = this.yearFrom === "" ? 0 : this.yearFrom
      let yearTo = this.yearTo === "" ? 4000 : this.yearTo
      MovieDataService.getAllMovies(page, size, this.sortBy, this.title, yearFrom, yearTo)
          .then(response => {
            this.movies = response.data.content
            this.totalItems = response.data.totalElements;
          })
          .catch(err => {
            alert(err)
          })
    },
    previousPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
        this.getMovies(this.currentPage - 1, this.pageSize);
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
        this.getMovies(this.currentPage - 1, this.pageSize);
      }
    }
  },
  computed: {
    totalPages() {
      return Math.ceil(this.totalItems / this.pageSize);
    }
  },
  mounted() {
    this.getMovies(0, 6)
  }
}
</script>

<style scoped>
  .card {
    box-shadow: 12px 12px 2px 1px rgba(0, 0, 255, .2);
    border: 1px solid black;
  }

  .desc {
    font-style: italic;
    opacity: 80%;
  }

  .title {
    display: inline-block;
    position: relative;
    text-decoration: none;
  }

  .title::after {
    content: "";
    position: absolute;
    bottom: 4px;
    left: 0;
    width: 100%;
    height: 13px;
    background-color: rgba(0, 0, 255, .2);
    transform: skew(-20deg) translateX(-5%);
  }

  .btn {
    box-shadow: 4px 4px 1px .5px rgba(0, 0, 255, .2);
    border: 1px solid black;
  }

  .btn:hover{
    background-color: rgba(0, 0, 255, .2);
    box-shadow: none;
    transition: all 0.5s ease 0.1s;
  }

  input {
    margin-right: 5px;
    border-radius: 5px;
    border: 1px solid black;
  }

  select {
    border-radius: 5px;
    border: 1px solid black;
  }

  input:focus, select:focus {
    box-shadow: 4px 4px 2px 1px rgba(0, 0, 255, .2);
    border: 1px solid rgba(0, 0, 255, .2);
  }
</style>
