package br.com.osvaldsoza.model.querys;

public enum PersonQuery {

    FIND_BY_ID("SELECT * FROM people WHERE id = ?"),
    FIND_BY_NAME("SELECT * FROM people WHERE name like ?"),
    FIND_ALL("SELECT * FROM people"),
    INSERT("INSERT INTO people(id,name,age)"
            + " VALUES(?,?,?)");

    private String query;

    PersonQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
