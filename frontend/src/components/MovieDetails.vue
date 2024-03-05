<template>
  <div class="container d-flex justify-content-center align-items-center min-vh-100 min-vw-100">
    <div class="d-flex justify-content-between align-items-center" style="width: 65vw;">
      <div class="d-flex flex-column">
        <div class="d-flex justify-content-between align-items-center">
          <h1 class="title">{{ movie.title }}</h1>
          <h4>({{ movie.year }})</h4>
          <h3><span style="color: rgba(0, 0, 255, .4)">★</span>{{ movie.averageRating }}</h3>
        </div>
        <h4>{{ movie.genre }}</h4>
        <h4 class="desc">{{ movie.description }}</h4>
      </div>
      <div class="d-flex flex-column ml-5">
        <div v-for="review in movie.reviews" :key="review.id" class="card mb-4">
          <div class="card-body">
            <h4>{{ review.comment }}</h4>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MovieDataService from "@/services/MovieDataService";

export default {
  name: "MovieDetails",
  data() {
    return {
      movie: "",
    }
  },
  methods: {
    getMovie(id) {
      MovieDataService.getById(id)
          .then(response => {
            this.movie = response.data
            this.movie.averageRating = this.movie.averageRating.toFixed(2)
          })
          .catch(err => {
            alert(err)
          })
    }
  },
  mounted() {
    this.getMovie(this.$route.params.id)
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
</style>