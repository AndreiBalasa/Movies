package ro.qual.movieRentals.customExceptions;


public class RentNotAllowedException extends  RuntimeException{
    public RentNotAllowedException(){
        super("Movie can not be rented due to expired return date");
    }
}
