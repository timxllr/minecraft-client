package de.crazymemecoke.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.minecraft.client.Minecraft;
import de.crazymemecoke.features.ui.guiscreens.GuiMainMenu;

public final class Value<T>
{
	  private T value;
	  private final T defaultValue;
	  private final String name;
	  public boolean isValueBoolean = false;
	  public boolean isValueInteger = false;
	  public boolean isValueFloat = false;
	  public boolean isValueDouble = false;
	  public boolean isValueLong = false;
	  public boolean isValueByte = false;
	  public static final List<Value> list = new ArrayList();
	  
	  public Value(String name, T value)
	  {
	    this.defaultValue = value;
	    this.name = name;
	    this.value = value;
	    if ((value instanceof Boolean)) {
	      this.isValueBoolean = true;
	    } else if ((value instanceof Integer)) {
	      this.isValueInteger = true;
	    } else if ((value instanceof Float)) {
	      this.isValueFloat = true;
	    } else if ((value instanceof Double)) {
	      this.isValueDouble = true;
	    } else if ((value instanceof Long)) {
	      this.isValueLong = true;
	    } else if ((value instanceof Byte)) {
	      this.isValueByte = true;
	    }
	    list.add(this);
	    Collections.sort(list, new Comparator()
	    {
	      public int compare(Value val1, Value val2)
	      {
	        return val1.getValueName().compareTo(val2.getValueName());
	      }

		@Override
		public int compare(Object arg0, Object arg1) {
			// TODO Auto-generated method stub
			return 0;
		}
	    });
	    Minecraft.mc().displayGuiScreen(new GuiMainMenu());
	  }
	  
	  public final String getValueName()
	  {
	    return this.name;
	  }
	  
	  public final T getDefaultValue()
	  {
	    return (T)this.defaultValue;
	  }
	  
	  public final T getValueState()
	  {
	    return (T)this.value;
	  }
	  
}