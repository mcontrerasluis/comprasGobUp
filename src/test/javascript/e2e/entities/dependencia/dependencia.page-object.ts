import { element, by, ElementFinder } from 'protractor';

export class DependenciaComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-dependencia div table .btn-danger'));
    title = element.all(by.css('jhi-dependencia div h2#page-heading span')).first();

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

export class DependenciaUpdatePage {
    pageTitle = element(by.id('jhi-dependencia-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    ramoInput = element(by.id('field_ramo'));
    nombreRamoInput = element(by.id('field_nombreRamo'));
    unidadInput = element(by.id('field_unidad'));
    nombreUnidadInput = element(by.id('field_nombreUnidad'));
    contactoInput = element(by.id('field_contacto'));
    correoElectronicoInput = element(by.id('field_correoElectronico'));
    telefonoInput = element(by.id('field_telefono'));
    userSelect = element(by.id('field_user'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setRamoInput(ramo) {
        await this.ramoInput.sendKeys(ramo);
    }

    async getRamoInput() {
        return this.ramoInput.getAttribute('value');
    }

    async setNombreRamoInput(nombreRamo) {
        await this.nombreRamoInput.sendKeys(nombreRamo);
    }

    async getNombreRamoInput() {
        return this.nombreRamoInput.getAttribute('value');
    }

    async setUnidadInput(unidad) {
        await this.unidadInput.sendKeys(unidad);
    }

    async getUnidadInput() {
        return this.unidadInput.getAttribute('value');
    }

    async setNombreUnidadInput(nombreUnidad) {
        await this.nombreUnidadInput.sendKeys(nombreUnidad);
    }

    async getNombreUnidadInput() {
        return this.nombreUnidadInput.getAttribute('value');
    }

    async setContactoInput(contacto) {
        await this.contactoInput.sendKeys(contacto);
    }

    async getContactoInput() {
        return this.contactoInput.getAttribute('value');
    }

    async setCorreoElectronicoInput(correoElectronico) {
        await this.correoElectronicoInput.sendKeys(correoElectronico);
    }

    async getCorreoElectronicoInput() {
        return this.correoElectronicoInput.getAttribute('value');
    }

    async setTelefonoInput(telefono) {
        await this.telefonoInput.sendKeys(telefono);
    }

    async getTelefonoInput() {
        return this.telefonoInput.getAttribute('value');
    }

    async userSelectLastOption() {
        await this.userSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async userSelectOption(option) {
        await this.userSelect.sendKeys(option);
    }

    getUserSelect(): ElementFinder {
        return this.userSelect;
    }

    async getUserSelectedOption() {
        return this.userSelect.element(by.css('option:checked')).getText();
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

export class DependenciaDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-dependencia-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-dependencia'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
