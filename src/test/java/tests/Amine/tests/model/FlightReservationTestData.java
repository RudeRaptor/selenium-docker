package tests.Amine.tests.model;

public record FlightReservationTestData(String firstName,
                                        String LastName,
                                        String email,
                                        String password,
                                        String street,
                                        String city,
                                        String state,
                                        String zip,
                                        String passengersCount,
                                        String expectedPrice,
                                        String destination) {
}
