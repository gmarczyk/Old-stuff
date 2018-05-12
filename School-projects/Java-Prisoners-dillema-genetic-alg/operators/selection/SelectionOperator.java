package operators.selection;

import model.Population;

public interface SelectionOperator {

    Population selectFromPopulation(Population toSelectFrom);

}
