package com.app.delicare.common.enums;

public enum EFunction {
    TITLE("title"),
    DEPARTMENT("department"),
    AREA("areas"),
    USER("users"),
    REGION("region"),
    GROUP("groups"),
    ROLE("roles"),
    LEAD("lead"),
    OPPORTUNITY("opportunity"),
    TASK("task"),
    CUSTOMER360("customer360"),
    ACCOUNT("account"),
    CATEGORY("categorys"),
    COMPANY("company"),
    CHANNEL("channel"),
    SOURCE("source"),
    STAGE("stage"),
    RESULT("result"),
    NEXTSTEP("nextStep"),
    ROLES("roles"),
    FUNCTION("function"),
    ACTION("action"),
    NEED("need"),
    MENU_DAY("menuDay"),
    ORDERS("orders"),
    ORDER_DETAIL("orderDetail"),
    UNIT("units"),
    INGREDIENT("ingredients"),
    RECIPE("recipes"),
    RECIPE_INGREDIENT("recipeIngredient"),
    PRODUCT("products"),
    ;


    private final String value;

    EFunction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
