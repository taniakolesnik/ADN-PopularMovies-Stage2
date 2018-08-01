package uk.co.taniakolesnik.adn_popularmovies_part_2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;

/**
 * Created by tetianakolesnik on 24/07/2018.
 */

@Dao
public interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie favourite);

    @Query("SELECT * FROM favourites")
    List<Movie> selectAll();

    @Query("SELECT COUNT(*) FROM favourites WHERE movieId=:movieId")
    int getCountFromFavourites(int movieId);

    @Query("DELETE FROM favourites WHERE movieId=:movieId")
    void deleteFavourite(int movieId);

    @Query("DELETE FROM favourites")
    void deleteAll();
}
