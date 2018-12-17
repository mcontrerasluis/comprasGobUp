import { element, by, ElementFinder } from 'protractor';

export class LugarEntregaComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-lugar-entrega div table .btn-danger'));
    title = element.all(by.css('jhi-lugar-entrega div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async clickOnLastDeleteButton() {
        await this.deleteButtons.last().click();
    }

    async countDeleteButtons() {
        return this.deleteButtons.count();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class LugarEntregaUpdatePage {
    pageTitle = element(by.id('jhi-lugar-entrega-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    descripcionInput = element(by.id('field_descripcion'));
    estadoInput = element(by.id('field_estado'));
    municipioInput = element(by.id('field_municipio'));
    direccionInput = element(by.id('field_direccion'));
    latitudInput = element(by.id('field_latitud'));
    longitudInput = element(by.id('field_longitud'));
    dependenciaSelect = element(by.id('field_dependencia'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setDescripcionInput(descripcion) {
        await this.descripcionInput.sendKeys(descripcion);
    }

    async getDescripcionInput() {
        return this.descripcionInput.getAttribute('value');
    }

    async setEstadoInput(estado) {
        await this.estadoInput.sendKeys(estado);
    }

    async getEstadoInput() {
        return this.estadoInput.getAttribute('value');
    }

    async setMunicipioInput(municipio) {
        await this.municipioInput.sendKeys(municipio);
    }

    async getMunicipioInput() {
        return this.municipioInput.getAttribute('value');
    }

    async setDireccionInput(direccion) {
        await this.direccionInput.sendKeys(direccion);
    }

    async getDireccionInput() {
        return this.direccionInput.getAttribute('value');
    }

    async setLatitudInput(latitud) {
        await this.latitudInput.sendKeys(latitud);
    }

    async getLatitudInput() {
        return this.latitudInput.getAttribute('value');
    }

    async setLongitudInput(longitud) {
        await this.longitudInput.sendKeys(longitud);
    }

    async getLongitudInput() {
        return this.longitudInput.getAttribute('value');
    }

    async dependenciaSelectLastOption() {
        await this.dependenciaSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async dependenciaSelectOption(option) {
        await this.dependenciaSelect.sendKeys(option);
    }

    getDependenciaSelect(): ElementFinder {
        return this.dependenciaSelect;
    }

    async getDependenciaSelectedOption() {
        return this.dependenciaSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}

export class LugarEntregaDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-lugarEntrega-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-lugarEntrega'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
