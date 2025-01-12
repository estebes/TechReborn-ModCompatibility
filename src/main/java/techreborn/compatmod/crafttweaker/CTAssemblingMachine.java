/*
 * This file is part of TechReborn, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2018 TechReborn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package techreborn.compatmod.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import techreborn.api.Reference;
import techreborn.api.recipe.machines.AssemblingMachineRecipe;

@ZenClass("mods.techreborn.assemblingMachine")
public class CTAssemblingMachine extends CTGeneric {

	@ZenMethod
	@techreborn.compatmod.crafttweaker.ZenDocumentation("IItemStack output, IIngredient input1, IIngredient input2, int ticktime, int euTick")
	public static RecipeSettings addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int ticktime, int euTick) {
		Object oInput1 = techreborn.compatmod.crafttweaker.CraftTweakerCompat.toObject(input1);
		Object oInput2 = techreborn.compatmod.crafttweaker.CraftTweakerCompat.toObject(input2);

		AssemblingMachineRecipe r = new AssemblingMachineRecipe(oInput1, oInput2, techreborn.compatmod.crafttweaker.CraftTweakerCompat.toStack(output), ticktime, euTick);

		addRecipe(r);
		return new RecipeSettings(r);
	}

	@ZenMethod
	@techreborn.compatmod.crafttweaker.ZenDocumentation("IIngredient iIngredient")
	public static void removeInputRecipe(IIngredient iIngredient) {
		CraftTweakerAPI.apply(new RemoveInput(iIngredient, getMachineName()));
	}

	@ZenMethod
	@ZenDocumentation("IItemStack output")
	public static void removeRecipe(IItemStack output) {
		CraftTweakerAPI.apply(new Remove(CraftTweakerCompat.toStack(output), getMachineName()));
	}
	
	@ZenMethod
	public static void removeAll(){
		CraftTweakerAPI.apply(new RemoveAll(getMachineName()));
	}

	public static String getMachineName() {
		return Reference.ASSEMBLING_MACHINE_RECIPE;
	}

}
