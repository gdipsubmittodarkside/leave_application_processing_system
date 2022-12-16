package sg.nus.iss.team2.model;

import java.util.Objects;


public class Approve {
 
  private String decision;

    private String comment;

    public Approve(){}

    public Approve(String decision, String comment)
    {
        this.decision = decision;
        this.comment = comment;
    }

    public String getComment()
    {
        return comment;
    }

    public String setComment(String comment)
    {
        return this.comment = comment;
    }

    public String getDecision()
    {
        return decision;
    }

    public String setDecision(String decision)
    {
        return this.decision = decision;
    }

    @Override
    public String toString() {
      return "Approve [decision=" + decision + "]";
    }
  
    @Override
    public int hashCode() {
      return Objects.hash(decision);
    }
  
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Approve other = (Approve) obj;
      return Objects.equals(decision, other.decision);
    }
}

