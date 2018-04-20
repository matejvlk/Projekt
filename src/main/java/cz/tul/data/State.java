package cz.tul.data;

public class State {

    private String stateName;

    private boolean enabled = false;
    private String authority;

    public State() {

    }

    public State(String stateName, boolean enabled, String authority) {
        this.stateName = stateName;
        this.enabled = enabled;
        this.authority = authority;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getStateName() {
        return stateName;
    }
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((authority == null) ? 0 : authority.hashCode());
        result = prime * result + (enabled ? 1231 : 1237);
        result = prime * result + ((stateName == null) ? 0 : stateName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        State other = (State) obj;
        if (authority == null) {
            if (other.authority != null)
                return false;
        } else if (!authority.equals(other.authority))
            return false;
        if (enabled != other.enabled)
            return false;
        if (stateName == null) {
            if (other.stateName != null)
                return false;
        } else if (!stateName.equals(other.stateName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "State [stateName=" + stateName + ", enabled=" + enabled + ", authority=" + authority + "]";
    }
}
