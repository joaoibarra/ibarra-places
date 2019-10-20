package com.ibarra.places.dao.restaurants

import com.ibarra.places.data.remote.domain.LocationRepository
import com.ibarra.places.data.remote.domain.RestaurantRepository
import com.ibarra.places.data.remote.domain.UserRatingRepository

val restaurantRepository = RestaurantRepository (
    id = "6702374",
    name = "Fasano - Hotel Fasano",
    url = "https:\\/\\/www.zomato.com\\/sao-paulo-sp\\/fasano-jardim-paulista?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1",
    location = LocationRepository(
        address = "Hotel Fasano, Rua Vittorio Fasano, 88, Jardim Paulista, S\u00e3o Paulo 10000",
        locality = "Hotel Fasano, Jardim Paulista",
        city = "S\u00e3o Paulo",
        latitude = -23.5643333333,
        longitude = -46.6698333333,
        zipcode = "10000",
        countryId = 30
    ),
    averageCostForTwo = 500.0,
    priceRange = 4,
    currency = "R\$",
    thumb = "https:\\/\\/b.zmtcdn.com\\/data\\/reviews_photos\\/85f\\/8752afa8442a7df4d69883fae545185f_1533020901.jpg?fit=around%7C200%3A200&crop=200%3A200%3B%2A%2C%2A",
    featuredImage = "",
    photosUrl = "https:\\/\\/www.zomato.com\\/sao-paulo-sp\\/fasano-jardim-paulista\\/photos?utm_source=api_basic_user&utm_medium=api&utm_campaign=v2.1#tabtop",
    eventsUrl = "",
    menuUrl = "",
    userRating = UserRatingRepository(
        aggregateRating = 4.1,
        ratingText = "Very Good",
        ratingColor = "5BA829",
        votes = 667
    ),
    allReviewsCount = 200,
    phoneNumber = "+55 11 987654321",
    cuisines = "Internacional"
)