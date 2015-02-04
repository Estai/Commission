package com.epam;

import com.epam.entity.PriorityStatement;

public class ApplicationManager {
    public static PriorityStatement getPriority(Integer sizeApplication)
    {  PriorityStatement priorityStatement=null;
        switch (sizeApplication)
        {
            case 0: priorityStatement=PriorityStatement.FIRST; break;
            case 1: priorityStatement=PriorityStatement.SECOND; break;
            case 2: priorityStatement=PriorityStatement.THIRD; break;
            case 3: priorityStatement=PriorityStatement.FOURTH; break;
            case 4: priorityStatement=PriorityStatement.FIFTH; break;
        }
        return priorityStatement;
    }
}
