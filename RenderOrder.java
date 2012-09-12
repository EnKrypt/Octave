/*
Copyright (C) 2012 Arvind Kumar

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>

Please note that in the event that any source file or other resource in this project does not include the above header, it should be assumed to be under the same license.
*/

import java.util.Comparator;

/**
 * Sorts entities by y value for rendering order.
 */
public class RenderOrder implements Comparator<MapEntity>{
	@Override
	public int compare(MapEntity o1,MapEntity o2){
		return (o1.y+o1.my)-(o2.y+o2.my);
	}
}
