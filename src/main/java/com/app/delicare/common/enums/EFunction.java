package com.app.delicare.common.enums;

public enum EFunction {
    TITLE("title"),
    DEPARTMENT("department"),
    USER("users"),
    GROUP("group"),
    STAGE("stages"),
    MENU_DAY("menuDay"),
    ORDERS("order"),
    ORDER_DETAIL("orderDetail"),
    UNIT("unit"),
    INGREDIENT("ingredient"),
    RECIPE("recipe"),
    RECIPE_INGREDIENT("recipeIngredient"),
    PRODUCT("product"),
    PRODUCT_IMG("productImg"),
    MENU("menu"),
    MENU_PRODUCT("menuProduct"),
    PAYMENT("payment"),
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
