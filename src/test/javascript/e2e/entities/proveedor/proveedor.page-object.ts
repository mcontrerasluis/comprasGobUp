import { element, by, ElementFinder } from 'protractor';

export class ProveedorComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    deleteButtons = element.all(by.css('jhi-proveedor div table .btn-danger'));
    title = element.all(by.css('jhi-proveedor div h2#page-heading span')).first();

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

export class ProveedorUpdatePage {
    pageTitle = element(by.id('jhi-proveedor-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nombreInput = element(by.id('field_nombre'));
    razonSocialInput = element(by.id('field_razonSocial'));
    rFCInput = element(by.id('field_rFC'));
    domicilioFiscalInput = element(by.id('field_domicilioFiscal'));
    personaAutorizadaInput = element(by.id('field_personaAutorizada'));
    correoElectronicoInput = element(by.id('field_correoElectronico'));
    telefonoContactoInput = element(by.id('field_telefonoContacto'));
    telefonoContactoDosInput = element(by.id('field_telefonoContactoDos'));
    userSelect = element(by.id('field_user'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setNombreInput(nombre) {
        await this.nombreInput.sendKeys(nombre);
    }

    async getNombreInput() {
        return this.nombreInput.getAttribute('value');
    }

    async setRazonSocialInput(razonSocial) {
        await this.razonSocialInput.sendKeys(razonSocial);
    }

    async getRazonSocialInput() {
        return this.razonSocialInput.getAttribute('value');
    }

    async setRFCInput(rFC) {
        await this.rFCInput.sendKeys(rFC);
    }

    async getRFCInput() {
        return this.rFCInput.getAttribute('value');
    }

    async setDomicilioFiscalInput(domicilioFiscal) {
        await this.domicilioFiscalInput.sendKeys(domicilioFiscal);
    }

    async getDomicilioFiscalInput() {
        return this.domicilioFiscalInput.getAttribute('value');
    }

    async setPersonaAutorizadaInput(personaAutorizada) {
        await this.personaAutorizadaInput.sendKeys(personaAutorizada);
    }

    async getPersonaAutorizadaInput() {
        return this.personaAutorizadaInput.getAttribute('value');
    }

    async setCorreoElectronicoInput(correoElectronico) {
        await this.correoElectronicoInput.sendKeys(correoElectronico);
    }

    async getCorreoElectronicoInput() {
        return this.correoElectronicoInput.getAttribute('value');
    }

    async setTelefonoContactoInput(telefonoContacto) {
        await this.telefonoContactoInput.sendKeys(telefonoContacto);
    }

    async getTelefonoContactoInput() {
        return this.telefonoContactoInput.getAttribute('value');
    }

    async setTelefonoContactoDosInput(telefonoContactoDos) {
        await this.telefonoContactoDosInput.sendKeys(telefonoContactoDos);
    }

    async getTelefonoContactoDosInput() {
        return this.telefonoContactoDosInput.getAttribute('value');
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

export class ProveedorDeleteDialog {
    private dialogTitle = element(by.id('jhi-delete-proveedor-heading'));
    private confirmButton = element(by.id('jhi-confirm-delete-proveedor'));

    async getDialogTitle() {
        return this.dialogTitle.getAttribute('jhiTranslate');
    }

    async clickOnConfirmButton() {
        await this.confirmButton.click();
    }
}
