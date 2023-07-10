import zhCN from "./zh-cn";

export function getTemplateValue(template) {
    const keys = Object.keys(zhCN);
    const templateKey = template
        .replace(/\(/g, '')
        .replace(/\)/g, '')
        .replace(/\./g, '')
        .toLowerCase();
    for (let i = 0; i < keys.length; i++) {
        const key = keys[i];
        const formattedKey = key
            .replace(/\(/g, '')
            .replace(/\)/g, '')
            .replace(/\./g, '')
            .toLowerCase();
        if (formattedKey === templateKey) {
            return zhCN[key];
        }
    }
    return undefined;
}

export function customTranslate(template, replacements) {

    replacements = replacements || {};

    // Translate
    template = getTemplateValue(template) || template;
    // Replace
    return template.replace(/{([^}]+)}/g, function (_, key) {
        return replacements[key] || "{" + key + "}";
    });
}

export default {
    translate: ["value", customTranslate]
};
