package cp.models;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractModel implements Model{
	
	public abstract Long getId();
	@Override
	public abstract int hashCode();
	@Override
	public abstract boolean equals(Object obj);
	
}
