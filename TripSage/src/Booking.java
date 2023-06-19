class Booking {
    private String destination;
    private String flight;
    private String accommodation;
    private String transport;

    Booking(String destination, String flight, String accommodation, String transport) {
        this.destination = destination;
        this.flight = flight;
        this.accommodation = accommodation;
        this.transport = transport;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }
}