package model;

/**
 * This class represents an item on our menu.
 *
 * @author Cody Auter
 */
public class MenuItem
{

    private int id;
    private String name;
    private double price;
    private String description;

    public MenuItem()
    {
    }

    public MenuItem(int id, String name, double price, String desc)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = desc;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getprice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public int hashCode()
    {
        int hash = 9;
        hash = 92 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final MenuItem other = (MenuItem) obj;
        if (this.id != other.id)
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "MenuItem{" + "id=" + id + ", name=" + name
                + ", price=" + price + ", description="
                + description + '}';
    }
}
