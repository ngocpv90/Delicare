package com.app.delicare.common.enums;

public enum EFunction {
    TITLE("title"),
    DEPARTMENT("department"),
    USER("users"),
    GROUP("groups"),
    STAGE("stages"),
    MENU_DAY("menuDay"),
    ORDERS("orders"),
    ORDER_DETAIL("orderDetail"),
    UNIT("units"),
    INGREDIENT("ingredients"),
    RECIPE("recipes"),
    RECIPE_INGREDIENT("recipeIngredient"),
    PRODUCT("products"),
    PRODUCT_IMG("productImg"),
    MENU("menus"),
    MENU_PRODUCT("menuProduct"),
    PAYMENT("payments"),
    SHIPPING("shipping"),
    CATEGORY("category"),
    ACTION("action"),
    FUNCTION("function"),
    FUNCTION_ACTION("functionAction"),
    PROVINCE("province"),
    DISTRICT("district"),
    WARD("ward"),
    ROLE("role"),
    ROLE_FUNCTION("roleFunction"),
    ;


    private final String value;

    EFunction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
